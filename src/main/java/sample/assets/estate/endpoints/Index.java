package sample.assets.estate.endpoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import sample.assets.estate.dtos.UserDetailsDTO;
import sample.assets.estate.models.User;
import sample.assets.estate.repositories.Groups;
import sample.assets.estate.services.AccessService;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/")
public class Index {

    private static final Logger LOG = LoggerFactory.getLogger(Index.class);

    private final AccessService accessService;
    private final Groups groups;

    public Index(AccessService accessService, Groups groups) {
        this.accessService = accessService;
        this.groups = groups;
    }

    @GetMapping
    public ModelAndView index() {
        LOG.info("index page ");
        return new ModelAndView("pages/index");
    }

    @GetMapping("menu")
    public ModelAndView menu(@AuthenticationPrincipal UserDetails userDetails) {
        LOG.info("menu fragment");
        UserDetailsDTO userDetailsDTO = (UserDetailsDTO) userDetails;
        List<String> groups = userDetailsDTO.getGroups();
        Map<String, Object> model = Map.of(
                "user", userDetails,
                "groups", groups);
        return new ModelAndView("components/app-menu", model);
    }

    @PostMapping
    public String afterLogin() {
        LOG.info("redirect after login");
        return "redirect:/";
    }
}
