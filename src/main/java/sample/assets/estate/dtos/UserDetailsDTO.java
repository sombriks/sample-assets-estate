package sample.assets.estate.dtos;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sample.assets.estate.models.Group;
import sample.assets.estate.models.Login;
import sample.assets.estate.models.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsDTO implements UserDetails {

    private final String username;
    private final String password;
    private final Long userId;
    private final List<Group> groups  = new ArrayList<>();

    public UserDetailsDTO(Login login, User user){
        username = login.getEmail();
        password = login.getChallenge();
        userId = user.getId();
        groups.addAll(user.getGroups());
    }

    public Long getUserId(){
        return userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return groups;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
