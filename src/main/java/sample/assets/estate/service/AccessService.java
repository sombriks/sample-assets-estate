package sample.assets.estate.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import sample.assets.estate.dto.RegisterDTO;
import sample.assets.estate.models.*;
import sample.assets.estate.repositories.Departments;
import sample.assets.estate.repositories.Groups;
import sample.assets.estate.repositories.Logins;
import sample.assets.estate.repositories.Users;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AccessService {

    private final Users users;
    private final Logins logins;
    private final Groups groups;
    private final Departments  departments;

    public AccessService(
            Users users,
            Logins logins,
            Groups groups,
            Departments  departments) {
        this.users = users;
        this.logins = logins;
        this.groups = groups;
        this.departments = departments;
    }

    public boolean valid(String token) {
        // check user from token
        // check if still valid
        return token != null;
    }

    public String signIn(String email, String password) {
        // get user from auth
        Optional<Login> login = logins.findByPassword(email, password);
        // make a token
        return login.isPresent() ? "token:" + login.get().getUser().getId() : null;
    }

    @Transactional
    public String signUp(RegisterDTO form) {
        // create an user
        User user = form.fill(new User());
        user.setCreated(LocalDateTime.now());
        users.save(user);
        // create a login
        Login login = form.fill(new Login());
        login.setUser(user);
        login.setLoginType(LoginType.PASSWORD);
        login.setCreated(LocalDateTime.now());
        logins.save(login);
        // add into group, if any
        Optional<Group> group = groups.findById(form.getGroupId());
        if (group.isPresent()) {
            user.getGroups().add(group.get());
        }
        // add into department, if any
        Optional<Department> department = departments.findById(form.getDepartmentId());
        if (department.isPresent()) {
            user.getDepartments().add(department.get() );
        }
        user.getLogins().add(login);
        user.setUpdated(LocalDateTime.now());
        users.save(user);
        // make a token
        return "token:" + user.getId();
    }
}
