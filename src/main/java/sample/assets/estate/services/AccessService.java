package sample.assets.estate.services;

import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sample.assets.estate.dtos.RegisterDTO;
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
    private final PasswordEncoder passwordEncoder;

    public AccessService(
            Users users,
            Logins logins,
            Groups groups,
            Departments  departments,
            PasswordEncoder passwordEncoder) {
        this.users = users;
        this.logins = logins;
        this.groups = groups;
        this.departments = departments;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User signUp(RegisterDTO form) {
        // create a user
        User user = form.fill(new User());
        user.setCreated(LocalDateTime.now());
        users.save(user);
        // create a login
        Login login = form.fill(new Login());
        login.setUser(user);
        login.setLoginType(LoginType.PASSWORD);
        login.setCreated(LocalDateTime.now());
        login.setChallenge(passwordEncoder.encode(login.getChallenge()));
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
        return user;
    }

    public Optional<User> findUserByEmail(String email) {
        return users.findByEmail(email);
    }
}
