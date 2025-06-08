package sample.assets.estate.endpoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import sample.assets.estate.models.User;
import sample.assets.estate.repositories.Groups;
import sample.assets.estate.service.AccessService;

import java.util.HashMap;
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
    public ModelAndView menu(@RequestHeader("X-Auth-Token") String token) {
        LOG.info("menu fragment");
        User user = accessService.findUser(token);
        if (user == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        Set<String> groupNames = groups.listNamesFor(user.getId());
        Map<String, Object> model = Map.of(
                "user", user,
                "groups", groupNames);
        return new ModelAndView("components/menu", model);
    }
}
