package sample.assets.estate.endpoints;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/buildings")
public class Buildings {

    @GetMapping
    public String index() { return "pages/buildings"; }
}
