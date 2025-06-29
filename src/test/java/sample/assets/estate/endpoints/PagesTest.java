package sample.assets.estate.endpoints;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import sample.assets.estate.configurations.TestSecurityConfig;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PagesTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldGetAssetsStatuses() {
        String result = restTemplate.getForObject("/assets/status//options", String.class);
        assertThat(result, containsString("Available"));
    }

    @Test
    public void shouldGetAssetsChangeReasons() {
        String result = restTemplate.getForObject("/assets/change-reasons//options", String.class);
        assertThat(result, containsString("Available"));
    }

    @Test
    public void shouldGetBuildings() {
        String result = restTemplate.getForObject("/buildings", String.class);
        assertThat(result, containsString("Buildings management"));
    }

    @Test
    public void shouldGetConsumables() {
        String result = restTemplate.getForObject("/consumables", String.class);
        assertThat(result, containsString("Consumables management"));
    }

    @Test
    public void shouldGetDepartments() {
        String result = restTemplate.getForObject("/departments", String.class);
        assertThat(result, containsString("Unauthorized"));
    }

    @Test
    public void shouldGetFurniture() {
        String result = restTemplate.getForObject("/furniture", String.class);
        assertThat(result, containsString("Furniture management"));
    }

    @Test
    public void shouldGetIndex() {
        String result = restTemplate.getForObject("/", String.class);
        assertThat(result, containsString("Assets Administrator"));
    }

    @Test
    public void shouldGetProfile() {
        String result = restTemplate.getForObject("/profile", String.class);
        assertThat(result, containsString("User profile"));
    }

    @Test
    public void shouldGetUsers() {
        String result = restTemplate.getForObject("/users", String.class);
        assertThat(result, containsString("Unauthorized"));
    }

    @Test
    public void shouldGetVehicles() {
        String result = restTemplate.getForObject("/vehicles", String.class);
        assertThat(result, containsString("Vehicles management"));
    }

}

