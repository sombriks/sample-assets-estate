package sample.assets.estate.endpoints;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import sample.assets.estate.configurations.NoSecurityConfig;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

@ActiveProfiles("test")
@Import(NoSecurityConfig.class)
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
