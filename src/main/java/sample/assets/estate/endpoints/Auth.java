package sample.assets.estate.endpoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import sample.assets.estate.service.AccessService;

import java.util.Map;

@Controller
@RequestMapping("/auth")
public class Auth {

    private static final Logger LOG = LoggerFactory.getLogger(Auth.class);

    private final AccessService accessService;

    public Auth(AccessService accessService) {
        this.accessService = accessService;
    }

    @GetMapping
    public String index() {
        LOG.info("Accessing the auth page");
        return "pages/auth";
    }

    @PostMapping
    public ModelAndView signIn(String email, String password) {
        LOG.info("Signing in");
        Map<String, String> model = Map.of("token", accessService.signIn(email, password));
        return new ModelAndView("fragments/set-token", model);
    }
}
