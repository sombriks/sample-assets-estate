package sample.assets.estate.endpoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import sample.assets.estate.models.User;
import sample.assets.estate.repositories.Users;
import sample.assets.estate.services.AccessService;

import java.time.LocalDateTime;
import java.util.Map;

@Controller
@RequestMapping("/profile")
public class Profile {

    private static final Logger LOG = LoggerFactory.getLogger(Profile.class);

    private final AccessService accessService;
    private final Users users;

    public Profile(AccessService accessService, Users users) {
        this.accessService = accessService;
        this.users = users;
    }

    @GetMapping
    public String index() {
        LOG.info("profile page");
        return "pages/profile";
    }

    @GetMapping("myself")
    public ModelAndView getMyself(@AuthenticationPrincipal UserDetails userDetails) {
        LOG.info("get user info");
        User user = accessService.findUserByEmail(userDetails.getUsername()).get();
        if (user == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        Map<String, Object> model = Map.of("user", user);
        return new ModelAndView("components/profile/user-info", model);
    }

    @PutMapping("myself")
    public ModelAndView putMyself(@AuthenticationPrincipal UserDetails userDetails, String name) {
        LOG.info("save user info");
        User user = accessService.findUserByEmail(userDetails.getUsername()).get();
        user.setName(name);
        user.setUpdated(LocalDateTime.now());
        users.save(user);
        Map<String, Object> model = Map.of("user", user,
                "message", "User info saved successfully");
        return new ModelAndView("components/profile/user-info", model);
    }

    @GetMapping("my-logins")
    public ModelAndView getMyLogins(@AuthenticationPrincipal UserDetails userDetails) {
        LOG.info("get user logins");
        User user = accessService.findUserByEmail(userDetails.getUsername()).get();
        Map<String, Object> model = Map.of("user", user);
        return new ModelAndView("components/profile/user-logins", model);
    }

    @GetMapping("my-groups")
    public ModelAndView getMyGroups(@AuthenticationPrincipal UserDetails userDetails) {
        LOG.info("get user groups");
        User user = accessService.findUserByEmail(userDetails.getUsername()).get();
        Map<String, Object> model = Map.of("user", user);
        return new ModelAndView("components/profile/user-groups", model);
    }

    @GetMapping("my-departments")
    public ModelAndView getMyDepartments(@AuthenticationPrincipal UserDetails userDetails) {
        LOG.info("get user departments");
        User user = accessService.findUserByEmail(userDetails.getUsername()).get();
        Map<String, Object> model = Map.of("user", user);
        return new ModelAndView("components/profile/user-departments", model);
    }
}
