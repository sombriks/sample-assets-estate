package sample.assets.estate.endpoints;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/furniture")
public class Furniture {

    @GetMapping
    public String index() { return "pages/furniture"; }
}
