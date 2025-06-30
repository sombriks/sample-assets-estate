package sample.assets.estate.endpoints;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import sample.assets.estate.configurations.BaseRequest;
import sample.assets.estate.configurations.TestSecurityConfig;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PagesTest {

    @Autowired
    private BaseRequest request;

    @Test
    public void shouldGetAssetsStatuses() {
        var result = request.get("/assets/status//options");
        assertThat(result.getBody(), containsString("Available"));
    }

    @Test
    public void shouldGetAssetsChangeReasons() {
        var result = request.get("/assets/change-reasons//options");
        assertThat(result.getBody(), containsString("Available"));
    }

    @Test
    public void shouldGetBuildings() {
        var result = request.get("/buildings");
        assertThat(result.getBody(), containsString("Buildings management"));
    }

    @Test
    public void shouldGetConsumables() {
        var result = request.get("/consumables");
        assertThat(result.getBody(), containsString("Consumables management"));
    }

    @Test
    public void shouldGetDepartments() {
        var result = request.getWithAuth("/departments");
        assertThat(result.getBody(), containsString("Departments management"));
    }

    @Test
    public void shouldGetFurniture() {
        var result = request.get("/furniture");
        assertThat(result.getBody(), containsString("Furniture management"));
    }

    @Test
    public void shouldGetIndex() {
        var result = request.getWithAuth("/");
        assertThat(result.getBody(), containsString("Assets Administrator"));
    }

    @Test
    public void shouldGetProfile() {
        var result = request.get("/profile");
        assertThat(result.getBody(), containsString("User profile"));
    }

    @Test
    public void shouldGetUsers() {
        var result = request.getWithAuth("/users");
        assertThat(result.getBody(), containsString("Users management"));
    }

    @Test
    public void shouldGetVehicles() {
        var result = request.get("/vehicles");
        assertThat(result.getBody(), containsString("Vehicles management"));
    }

}

