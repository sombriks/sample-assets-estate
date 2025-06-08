package sample.assets.estate.endpoints;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sample.assets.estate.endpoints.base.BaseEP;
import sample.assets.estate.models.Department;
import sample.assets.estate.repositories.Departments;
import sample.assets.estate.service.AccessService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/departments")
public class DepartmentsEP extends BaseEP {

    private final Departments repository;

    public DepartmentsEP(AccessService accessService, Departments repository) {
        super(accessService, "Admin", "Manager");
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
    public ModelAndView listDepartments(
            @RequestHeader("X-Auth-Token") String token,
            @RequestParam(required = false, defaultValue = "false") Boolean add,
            @RequestParam(required = false, defaultValue = "") String q) {
        checkPermission(token);
        List<Department> list = repository.findByNameContainsIgnoreCaseOrderByName(q);
        Map<String, Object> model = Map.of("departments", list, "q", q, "add", add);
        return new ModelAndView("components/departments/department-list", model);
    }

    @PostMapping("save")
    public ModelAndView saveDepartment(
            @RequestHeader("X-Auth-Token") String token,
            String departmentName) {
        checkPermission(token);
        Department department = new Department(departmentName);
        repository.save(department);
        List<Department> list = repository.findByNameContainsIgnoreCaseOrderByName("");
        Map<String, Object> model = Map.of("departments", list, "success", true);
        return new ModelAndView("components/departments/department-list", model);
    }

    @DeleteMapping("{id}")
    public ModelAndView removeDepartment(
            @RequestHeader("X-Auth-Token") String token,
            @PathVariable Long id) {
        checkPermission(token);
        repository.deleteById(id);
        List<Department> list = repository.findByNameContainsIgnoreCaseOrderByName("");
        Map<String, Object> model = Map.of("departments", list, "removed", true);
        return new ModelAndView("components/departments/department-list", model);
    }
}
