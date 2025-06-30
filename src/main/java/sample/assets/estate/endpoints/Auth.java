package sample.assets.estate.endpoints;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import sample.assets.estate.dtos.RegisterDTO;
import sample.assets.estate.models.Department;
import sample.assets.estate.models.Group;
import sample.assets.estate.repositories.Departments;
import sample.assets.estate.repositories.Groups;
import sample.assets.estate.services.AccessService;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/auth")
public class Auth {

    private static final Logger LOG = LoggerFactory.getLogger(Auth.class);

    private final AccessService accessService;
    private final Groups groupsRepository;
    private final Departments departmentsRepository;

    public Auth(
            AccessService accessService,
            Groups groupsRepository,
            Departments departmentsRepository) {
        this.accessService = accessService;
        this.groupsRepository = groupsRepository;
        this.departmentsRepository = departmentsRepository;
    }

    @GetMapping
    public ModelAndView index(
            @RequestParam(required = false, defaultValue = "false") Boolean created) {
        LOG.info("Accessing the auth page. Created: [{}]", created);
        return new ModelAndView("pages/auth", Map.of("created", created));
    }

    @GetMapping("login")
    public ModelAndView login() {
        LOG.info("Accessing the login mode");
        return new ModelAndView("components/auth/login-register", Map.of("mode", "login"));
    }

    @GetMapping("register")
    public ModelAndView register() {
        LOG.info("Accessing the register mode");
        return new ModelAndView("components/auth/login-register", Map.of("mode", "register"));
    }

    @GetMapping("groups/options")
    public ModelAndView groupsOptions(
            @RequestParam(required = false, defaultValue = "0") Long defaultValue) {
        List<Group> groups = groupsRepository.findAll();
        Map<String, Object> model = Map.of(
                "defaultValue", defaultValue,
                "groups", groups);
        return new ModelAndView("controls/select-group", model);
    }

    @GetMapping("departments/options")
    public ModelAndView departmentsOptions(
            @RequestParam(required = false, defaultValue = "0") Long defaultValue) {
        List<Department> departments = departmentsRepository.findAll();
        Map<String, Object> model = Map.of(
                "defaultValue", defaultValue,
                "departments", departments);
        return new ModelAndView("controls/select-department", model);
    }

    @PostMapping("register")
    public ResponseEntity signUp(@Valid @ModelAttribute RegisterDTO form) {
        LOG.info("Signing up");
        accessService.signUp(form);
        return ResponseEntity
                .status(HttpStatus.SEE_OTHER)
                .location(URI.create("/auth?created=true"))
                .build();
    }
}
