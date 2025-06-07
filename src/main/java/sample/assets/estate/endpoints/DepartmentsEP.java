package sample.assets.estate.endpoints;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import sample.assets.estate.models.Department;
import sample.assets.estate.models.User;
import sample.assets.estate.repositories.Departments;
import sample.assets.estate.service.AccessService;

@Controller
@RequestMapping("/departments")
public class DepartmentsEP {

    private final AccessService accessService;
    private final Departments repository;

    public DepartmentsEP(AccessService accessService, Departments repository) {
        this.accessService = accessService;
        this.repository = repository;
    }

    @GetMapping
    public String index() {
        return "pages/departments";
    }

    @GetMapping("options")
    public ModelAndView options(Long defaultValue) {
        List<Department> departments = repository.findAll();
        Map<String, Object> model = Map.of(
                "defaultValue", defaultValue,
                "departments", departments);
        return new ModelAndView("controls/select-department", model);
    }

    @GetMapping("list")
    public ModelAndView listDepartments(@RequestHeader("X-Auth-Token") String token, String q) {
        User user = accessService.findUser(token);
        if (user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        if (user.getGroups().stream().noneMatch(g ->
                g.getName().equals("Admin") || g.getName().equals("Manager"))) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User not allowed to list users");
        }
        if (q == null) q = "";
        List<Department> list = repository.findByNameContainsIgnoreCaseOrderByName(q);
        Map<String, Object> model = Map.of("departments", list, "q", q);
        return new ModelAndView("components/departments/department-list", model);
    }
}
