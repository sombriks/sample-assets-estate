package sample.assets.estate.endpoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import sample.assets.estate.dtos.RegisterDTO;
import sample.assets.estate.services.AccessService;

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

    @GetMapping("login")
    public ModelAndView login() {
        LOG.info("Accessing the login mode");
        return new ModelAndView("components/auth/login-register", Map.of("mode", "login"));
    }

    @GetMapping("register")
    public ModelAndView register() {
        LOG.info("Accessing the register mode");
        return new ModelAndView("components/auth/login-register", Map.of("mode", "register"));
    }

    @PostMapping("register")
    public ModelAndView signUp(@ModelAttribute RegisterDTO form) {
        LOG.info("Signing up");
        Map<String, Object> model = Map.of(
                "token", accessService.signUp(form),
                "mode", "token");
        return new ModelAndView("components/auth/login-register", model);
    }
}
