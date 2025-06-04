package sample.assets.estate.service;

import org.springframework.stereotype.Service;
import sample.assets.estate.dto.RegisterDTO;
import sample.assets.estate.repositories.Users;

@Service
public class AccessService {

    private final Users users;

    public AccessService(Users users) {
        this.users = users;
    }

    public boolean valid(String token) {
        // check user
        // check if present
        // check if still valid
        return token != null;
    }

    public String signIn(String email, String password) {
        return "a token";
    }

    public String signUp(RegisterDTO form) {
        return "a token";
    }
}
