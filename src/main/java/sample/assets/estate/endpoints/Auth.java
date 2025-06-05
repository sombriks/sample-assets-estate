package sample.assets.estate.endpoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import sample.assets.estate.dto.RegisterDTO;
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

    @GetMapping("login")
    public String login() {
        LOG.info("Accessing the login fragment");
        return "fragments/auth/login";
    }

    @GetMapping("register")
    public String register() {
        LOG.info("Accessing the register fragment");
        return "fragments/auth/register";
    }

    @PostMapping
    public ModelAndView signIn(String email, String password) {
        LOG.info("Signing in");
        String token = accessService.signIn(email, password);
        if (token == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        Map<String, String> model = Map.of("token", token);
        return new ModelAndView("fragments/auth/set-token", model);
    }

    @PostMapping("register")
    public ModelAndView signUp(@ModelAttribute RegisterDTO form) {
        LOG.info("Signing up");
        Map<String, Object> model = Map.of("token", accessService.signUp(form));
        return new ModelAndView("fragments/auth/set-token", model);
    }
}
