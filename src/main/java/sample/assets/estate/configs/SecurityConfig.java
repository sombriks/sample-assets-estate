package sample.assets.estate.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import sample.assets.estate.dtos.UserDetailsDTO;
import sample.assets.estate.models.Login;
import sample.assets.estate.models.User;
import sample.assets.estate.services.AccessService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private static final Logger LOG = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http,
            @Value("${spring.security.open-paths}") String[] urls,
            @Value("${spring.security.login-page}") String loginPage) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers(urls)
                                .permitAll()
                                .anyRequest().authenticated())
                .formLogin(formLogin -> formLogin
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .loginPage(loginPage)
                        .loginProcessingUrl(loginPage + "/login")
                        .successForwardUrl("/")
                        .permitAll())
                .logout(logout -> logout
                        .clearAuthentication(true)
                        .logoutUrl(loginPage + "/logout")
                        .logoutSuccessUrl("/")
                        .permitAll())
                .build();
    }

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
