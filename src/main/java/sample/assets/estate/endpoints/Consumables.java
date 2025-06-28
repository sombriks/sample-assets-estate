package sample.assets.estate.endpoints;

import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import sample.assets.estate.dtos.ConsumableDTO;
import sample.assets.estate.endpoints.base.BaseEP;
import sample.assets.estate.models.ConsumablePosition;
import sample.assets.estate.models.User;
import sample.assets.estate.repositories.ConsumablesPosition;
import sample.assets.estate.services.AccessService;
import sample.assets.estate.services.ConsumablesService;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/consumables")
public class Consumables extends BaseEP {

    private final ConsumablesService service;
    private final ConsumablesPosition repository;

    public Consumables(
            ConsumablesService service,
            ConsumablesPosition repository,
            AccessService accessService) {
        super(accessService, "Admin", "Manager", "Basic");
        this.repository = repository;
        this.service = service;
    }

    @GetMapping
    public String index() {
        return "pages/consumables";
    }

    @GetMapping("list")
    public ModelAndView listConsumables(
            @RequestHeader("X-Auth-Token") String token,
            @RequestParam(required = false, defaultValue = "") String q,
            @RequestParam(required = false, defaultValue = "false") Boolean add,
            @RequestParam(required = false, defaultValue = "false") Boolean created,
            @RequestParam(required = false, defaultValue = "false") Boolean updated,
            @RequestParam(required = false, defaultValue = "0") Long detailId) {
        User user = checkPermission(token);
        List<ConsumablePosition> consumables = service.listConsumables(q, user,
                Sort.by(Sort.Direction.ASC, "id"));
        ConsumablePosition detail = repository.findById(detailId).orElse(null);
        Map<String, Object> model = new HashMap();
        model.put("consumables", consumables);
        model.put("created", created);
        model.put("updated", updated);
        model.put("detail", detail);
        model.put("add", add);
        return new ModelAndView("components/consumables/list-consumables", model);
    }

    @PostMapping
    public ResponseEntity createConsumable(
            @RequestHeader("X-Auth-Token") String token,
            @ModelAttribute @Valid ConsumableDTO form
    ) {
        User user = checkPermission(token);

        var result = service.newConsumable(user, form);
        if (result == null)
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Unable to save consumable");

        // https://hypermedia.systems/htmx-patterns/#_a_response_code_gotcha
        return ResponseEntity
                .status(HttpStatus.SEE_OTHER)
                .location(URI.create("/consumables/list?created=true"))
                .build();
    }

    @PutMapping
    public ResponseEntity updateConsumable(
            @RequestHeader("X-Auth-Token") String token,
            @ModelAttribute @Valid ConsumableDTO form
    ) {
        User user = checkPermission(token);

        var result = service.updateConsumable(user, form);
        if (result == null)
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Unable to save consumable");

        return ResponseEntity
                .status(HttpStatus.SEE_OTHER)
                .location(URI.create("/consumables/list?updated=true"))
                .build();
    }
}
