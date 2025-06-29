package sample.assets.estate.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import sample.assets.estate.dtos.UserDetailsDTO;
import sample.assets.estate.models.Login;
import sample.assets.estate.models.User;
import sample.assets.estate.services.AccessService;

@Configuration
public class AuthConfig {

    private static final Logger LOG = LoggerFactory.getLogger(AuthConfig.class);

    @Bean
    public UserDetailsService userDetailsService(AccessService accessService) {
        return username -> {
            LOG.debug("username: [{}]", username);
            User user = accessService.findUserByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException(username));
            Login login = user.getLogins().stream().filter(l -> l.getEmail().equals(username)).findFirst()
                    .orElseThrow(() -> new UsernameNotFoundException(username));
            return new UserDetailsDTO(login, user);
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
