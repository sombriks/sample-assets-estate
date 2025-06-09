package sample.assets.estate.endpoints;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sample.assets.estate.dto.ConsumableDTO;
import sample.assets.estate.endpoints.base.BaseEP;
import sample.assets.estate.models.ConsumablePosition;
import sample.assets.estate.models.User;
import sample.assets.estate.service.AccessService;
import sample.assets.estate.service.ConsumablesService;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/consumables")
public class Consumables extends BaseEP {

    private final ConsumablesService service;

    public Consumables(
            ConsumablesService service,
            AccessService accessService) {
        super(accessService, "Admin", "Manager", "Basic");
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
            @RequestParam(required = false, defaultValue = "false") Boolean add) {
        User user = checkPermission(token);
        List<ConsumablePosition> consumables = service.listConsumables(q, user, Sort.by(Sort.Direction.ASC, "id"));
        Map<String, Object> model = Map.of("consumables", consumables, "add", add);
        return new ModelAndView("components/consumables/list-consumables", model);
    }

    @PostMapping
    public ResponseEntity createConsumable(
            @RequestHeader("X-Auth-Token") String token,
            @ModelAttribute ConsumableDTO form
    ) {
        User user = checkPermission(token);

        // https://hypermedia.systems/htmx-patterns/#_a_response_code_gotcha
        return ResponseEntity
                .status(HttpStatus.SEE_OTHER)
                .location(URI.create("/consumables/list"))
                .build();
    }
}
