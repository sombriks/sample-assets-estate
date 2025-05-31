package sample.assets.estate.endpoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sample.assets.estate.service.AccessService;

@Controller
@RequestMapping("/auth")
public class Auth {

    private static final Logger LOG =  LoggerFactory.getLogger(Auth.class);

    private final AccessService accessService;

    public Auth(AccessService accessService) {
        this.accessService = accessService;
    }

    @GetMapping
    public String index() {
        return "pages/auth";
    }
}
