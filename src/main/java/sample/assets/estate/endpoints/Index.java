package sample.assets.estate.endpoints;

import de.neuland.pug4j.spring.view.PugView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import sample.assets.estate.service.AccessService;

@Controller
@RequestMapping("/")
public class Index {

    private static final Logger LOG =  LoggerFactory.getLogger(Index.class);

    private final AccessService accessService;

    public Index(AccessService accessService) {
        this.accessService = accessService;
    }

    @GetMapping
    public ModelAndView index(@RequestHeader(name = "X-Auth-Token", required = false) String token) {
        if(accessService.valid(token))
            return new ModelAndView("pages/index");
        else
            return new ModelAndView("redirect:/auth");
    }
}
