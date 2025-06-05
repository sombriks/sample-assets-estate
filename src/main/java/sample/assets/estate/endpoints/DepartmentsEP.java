package sample.assets.estate.endpoints;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sample.assets.estate.models.Department;
import sample.assets.estate.repositories.Departments;

@Controller
@RequestMapping("/departments")
public class DepartmentsEP {

    private final Departments repository;

    public DepartmentsEP(Departments repository) {
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
}
