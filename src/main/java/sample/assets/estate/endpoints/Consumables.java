package sample.assets.estate.endpoints;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/consumables")
public class Consumables {

    @GetMapping
    public String index() { return "pages/consumables"; }
}
