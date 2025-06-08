package sample.assets.estate.endpoints;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sample.assets.estate.endpoints.base.BaseEP;
import sample.assets.estate.repositories.ConsumablesPosition;
import sample.assets.estate.service.AccessService;

import java.util.Map;

@Controller
@RequestMapping("/consumables")
public class Consumables extends BaseEP {

    private final ConsumablesPosition repository;

    public Consumables(
            ConsumablesPosition repository,
            AccessService accessService) {
        super(accessService, "Admin", "Manager", "Basic");
        this.repository = repository;
    }

    @GetMapping
    public String index() {
        return "pages/consumables";
    }

    @GetMapping("list")
    public ModelAndView listConsumables(
            @RequestHeader("X-Auth-Token") String token,
            @RequestParam(required = false, defaultValue = "") String q) {
        checkPermission(token);
        Map<String, Object> model = Map.of();
        return new ModelAndView("components/consumables/list-consumables", model);
    }
}
