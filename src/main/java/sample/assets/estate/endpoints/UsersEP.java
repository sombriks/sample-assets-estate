package sample.assets.estate.endpoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import sample.assets.estate.models.User;
import sample.assets.estate.repositories.Users;
import sample.assets.estate.services.AccessService;

import java.time.LocalDateTime;
import java.util.List;
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
    @PreAuthorize("hasAuthority('Admin')")
    public String index() {
        LOG.info("users page");
        return "pages/users";
    }

    @GetMapping("list")
    @PreAuthorize("hasAuthority('Admin')")
    public ModelAndView listUsers(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(required = false, defaultValue = "") String q) {
        List<User> list = users.findByNameContainsIgnoreCaseOrderByName(q);
        Map<String, Object> model = Map.of("user", userDetails, "users", list, "q", q);
        return new ModelAndView("components/users/user-list", model);
    }
}


