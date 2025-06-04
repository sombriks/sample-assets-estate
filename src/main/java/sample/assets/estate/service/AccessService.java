package sample.assets.estate.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import sample.assets.estate.dto.RegisterDTO;
import sample.assets.estate.models.Login;
import sample.assets.estate.models.LoginType;
import sample.assets.estate.models.User;
import sample.assets.estate.repositories.Logins;
import sample.assets.estate.repositories.Users;

import java.time.LocalDateTime;

@Service
public class AccessService {

    private final Users users;
    private final Logins logins;

    public AccessService(Users users, Logins logins) {
        this.users = users;
        this.logins = logins;
    }

    public boolean valid(String token) {
        // check user from token
        // check if still valid
        return token != null;
    }

    public String signIn(String email, String password) {
        // get user from auth
        Login login = logins.findByPassword(email, password);
        // make a token
        return "token:" + login.getUser().getId();
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
        // make a token
        return "token:" + user.getId();
    }
}
