package sample.assets.estate.endpoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import sample.assets.estate.endpoints.base.BaseEP;
import sample.assets.estate.models.User;
import sample.assets.estate.repositories.Users;
import sample.assets.estate.service.AccessService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/users")
public class UsersEP extends BaseEP {

    private static final Logger LOG = LoggerFactory.getLogger(UsersEP.class);

    private final AccessService accessService;
    private final Users users;

    public UsersEP(AccessService accessService, Users users) {
        super(accessService, "admin");
        this.accessService = accessService;
        this.users = users;
    }

    @GetMapping
    public String index() {
        LOG.info("users page");
        return "pages/users";
    }

    @GetMapping("list")
    public ModelAndView listUsers(
            @RequestHeader(name = "X-Auth-Token") String token,
            @RequestParam(required = false, defaultValue = "") String q) {
        User user = checkPermission(token);
        List<User> list = users.findByNameContainsIgnoreCaseOrderByName(q);
        Map<String, Object> model = Map.of("user", user, "users", list, "q", q);
        return new ModelAndView("components/users/user-list", model);
    }

    @GetMapping("myself")
    public ModelAndView getMyself(@RequestHeader("X-Auth-Token") String token) {
        LOG.info("get user info");
        User user = accessService.findUser(token);
        if (user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        Map<String, Object> model = Map.of("user", user);
        return new ModelAndView("components/profile/user-info", model);
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
        return new ModelAndView("components/profile/user-info", model);
    }

    @GetMapping("my-logins")
    public ModelAndView getMyLogins(@RequestHeader("X-Auth-Token") String token) {
        LOG.info("get user logins");
        User user = accessService.findUser(token);
        if (user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        Map<String, Object> model = Map.of("user", user);
        return new ModelAndView("components/profile/user-logins", model);
    }

    @GetMapping("my-groups")
    public ModelAndView getMyGroups(@RequestHeader("X-Auth-Token") String token) {
        LOG.info("get user groups");
        User user = accessService.findUser(token);
        if (user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        Map<String, Object> model = Map.of("user", user);
        return new ModelAndView("components/profile/user-groups", model);
    }

    @GetMapping("my-departments")
    public ModelAndView getMyDepartments(@RequestHeader("X-Auth-Token") String token) {
        LOG.info("get user departments");
        User user = accessService.findUser(token);
        if (user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        Map<String, Object> model = Map.of("user", user);
        return new ModelAndView("components/profile/user-departments", model);
    }

}
