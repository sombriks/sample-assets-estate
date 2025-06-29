package sample.assets.estate.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("!test")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

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
}
