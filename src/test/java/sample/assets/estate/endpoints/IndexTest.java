package sample.assets.estate.endpoints;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

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
        String result = doGet("/menu", "token:1");
        assertThat(result, containsString("Dashboard"));

    }

    @Test
    public void shouldNotGetMenu() {
        String result = doGet("/menu", "token:9999");
        assertThat(result, containsString("Bad Request"));
    }

    private String doGet(String uri, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Auth-Token", token);
        String result = restTemplate.exchange(uri, HttpMethod.GET,
                new HttpEntity<>(headers),
                String.class).getBody();
        return result;
    }
}

