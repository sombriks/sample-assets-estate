package sample.assets.estate.endpoints;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sample.assets.estate.models.Group;
import sample.assets.estate.repositories.Groups;

@Controller
@RequestMapping("/groups")
public class GroupsEP {

    private final Groups repository;

    public GroupsEP(Groups repository) {
        this.repository = repository;
    }

    @GetMapping("options")
    public ModelAndView options(Long defaultValue) {
        List<Group> groups = repository.findAll();
        Map<String, Object> model = Map.of(
                "defaultValue", defaultValue,
                "groups", groups);
        return new ModelAndView("controls/select-group", model);
    }
}
