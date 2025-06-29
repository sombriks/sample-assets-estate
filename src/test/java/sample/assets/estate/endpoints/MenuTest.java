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
public class MenuTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Disabled("needs a better test security filter")
    public void shouldRenderMenu() {
        var result = doGet("/menu", "token:1");
        assertThat(result.getStatusCode().value(), equalTo(200));
        assertThat(result.getBody(), containsString("Dashboard"));

    }

    @Test
    public void shouldNotGetMenu() {
        var result = doGet("/menu", "token:9999");
        assertThat(403, equalTo(result.getStatusCode().value()));
        assertThat(result.getBody(), containsString("Forbidden"));
    }

    private ResponseEntity<String> doGet(String uri, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Auth-Token", token);
        return restTemplate.exchange(uri, HttpMethod.GET,
                new HttpEntity<>(headers),
                String.class);
    }
}
