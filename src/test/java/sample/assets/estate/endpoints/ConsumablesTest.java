package sample.assets.estate.endpoints;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConsumablesTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldGetIndex() {
        String result = restTemplate.getForObject("/consumables", String.class);
        assertThat(result, containsString("Consumables management"));
    }
}
