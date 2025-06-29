package sample.assets.estate.endpoints;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import sample.assets.estate.configurations.NoSecurityConfig;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

@ActiveProfiles("test")
@Import(NoSecurityConfig.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PagesTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldGetConsumables() {
        String result = restTemplate.getForObject("/consumables", String.class);
        assertThat(result, containsString("Consumables management"));
    }

    @Test
    public void shouldGetIndex() {
        String result = restTemplate.getForObject("/", String.class);
        assertThat(result, containsString("Assets Administrator"));
    }

    @Test
    public void shouldGetUsers() {
        String result = restTemplate.getForObject("/users", String.class);
        assertThat(result, containsString("Forbidden"));
    }

}

