package sample.assets.estate.configs;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import sample.assets.estate.dtos.UserDetailsDTO;
import sample.assets.estate.models.Login;
import sample.assets.estate.models.User;
import sample.assets.estate.services.AccessService;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final Logger LOG = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http,
            JwtDecoder decoder,
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
                        .successForwardUrl("/"))
                .logout(LogoutConfigurer::permitAll)
                .oauth2ResourceServer(conf -> conf
                        .jwt(jwt -> jwt
                                .decoder(decoder))).build();
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

    @Bean
    public JwtDecoder jwtDecoder(
            @Value("${spring.security.public-key}") RSAPublicKey publicKey) {
        return NimbusJwtDecoder.withPublicKey(publicKey).build();
    }

    @Bean
    public JwtEncoder jwtEncoder(
            @Value("${spring.security.public-key}") RSAPublicKey publicKey,
            @Value("${spring.security.private-key}") RSAPrivateKey privateKey) {
        JWK jwk = new RSAKey.Builder(publicKey).privateKey(privateKey).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }
}
