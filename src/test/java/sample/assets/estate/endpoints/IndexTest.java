package sample.assets.estate.endpoints;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IndexTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldGetIndex() {
        String result = restTemplate.getForObject("/", String.class);
        assertThat(result, containsString("Assets Administrator"));
    }

    @Test
    public void shouldRenderMenu() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Auth-Token", "token:1");
        String result = restTemplate.exchange("/menu",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                String.class).getBody();
        assertThat(result, containsString("Dashboard"));

    }
}

