package sample.assets.estate.endpoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import sample.assets.estate.models.User;
import sample.assets.estate.repositories.Users;
import sample.assets.estate.service.AccessService;

import java.time.LocalDateTime;
import java.util.Map;

@Controller
@RequestMapping("/users")
public class UsersEP {

    private static final Logger LOG = LoggerFactory.getLogger(UsersEP.class);

    private final AccessService accessService;
    private final Users users;

    public UsersEP(AccessService accessService, Users users) {
        this.accessService = accessService;
        this.users = users;
    }

    @GetMapping
    public String index() {
        LOG.info("users page");
        return "pages/users";
    }

    @GetMapping("myself")
    public ModelAndView getMyself(@RequestHeader("X-Auth-Token") String token) {
        LOG.info("get user info");
        User user = accessService.findUser(token);
        if (user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        Map<String, Object> model = Map.of("user", user);
        return new ModelAndView("components/user-info", model);
    }

    @PutMapping("myself")
    public ModelAndView putMyself(@RequestHeader("X-Auth-Token") String token, String name) {
        LOG.info("save user info");
        User user = accessService.findUser(token);
        if (user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        user.setName(name);
        user.setUpdated(LocalDateTime.now());
        users.save(user);
        Map<String, Object> model = Map.of("user", user,
                "message", "User info saved successfully");
        return new ModelAndView("components/user-info", model);
    }
}
