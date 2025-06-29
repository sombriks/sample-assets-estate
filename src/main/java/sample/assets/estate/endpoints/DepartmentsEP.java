package sample.assets.estate.endpoints;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sample.assets.estate.models.Department;
import sample.assets.estate.repositories.Departments;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/departments")
public class DepartmentsEP {

    private final Departments repository;

    public DepartmentsEP(Departments repository) {
        this.repository = repository;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('Admin','Manager')")
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
    @PreAuthorize("hasAnyAuthority('Admin','Manager')")
    public ModelAndView listDepartments(
            @RequestParam(required = false, defaultValue = "false") Boolean add,
            @RequestParam(required = false, defaultValue = "") String q) {
        List<Department> list = repository.findByNameContainsIgnoreCaseOrderByName(q);
        Map<String, Object> model = Map.of("departments", list, "q", q, "add", add);
        return new ModelAndView("components/departments/department-list", model);
    }

    @PostMapping("save")
    @PreAuthorize("hasAnyAuthority('Admin','Manager')")
    public ModelAndView saveDepartment(
            String departmentName) {
        Department department = new Department(departmentName);
        repository.save(department);
        List<Department> list = repository.findByNameContainsIgnoreCaseOrderByName("");
        Map<String, Object> model = Map.of("departments", list, "success", true);
        return new ModelAndView("components/departments/department-list", model);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyAuthority('Admin','Manager')")
    public ModelAndView removeDepartment(
            @PathVariable Long id) {
        repository.deleteById(id);
        List<Department> list = repository.findByNameContainsIgnoreCaseOrderByName("");
        Map<String, Object> model = Map.of("departments", list, "removed", true);
        return new ModelAndView("components/departments/department-list", model);
    }
}
