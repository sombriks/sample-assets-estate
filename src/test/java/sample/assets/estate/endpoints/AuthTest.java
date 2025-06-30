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
import static org.hamcrest.Matchers.equalTo;

@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthTest {

    @Autowired
    private BaseRequest request;

    @Test
    public void shouldGetLoginComponent() {
        var result = request.get("/auth/login");
        assertThat(result.getStatusCode().value(), equalTo(200));
        assertThat(result.getBody(), containsString("Sign in"));
    }

    @Test
    public void shouldGetSignUpComponent() {
        var result = request.get("/auth/register");
        assertThat(result.getStatusCode().value(), equalTo(200));
        assertThat(result.getBody(), containsString("Sign up"));
    }

    @Test
    public void shouldGetGroupsOptions() {
        var result = request.get("/auth/groups/options");
        assertThat(result.getStatusCode().value(), equalTo(200));
        assertThat(result.getBody(), containsString("Choose"));
    }

    @Test
    public void shouldGetDepartmentsOptions() {
        var result = request.get("/auth/departments/options");
        assertThat(result.getStatusCode().value(), equalTo(200));
        assertThat(result.getBody(), containsString("Choose"));
    }

    @Test
    public void shouldCreateUser() {
        var form = "name=test2&email=test2@test.com3&password=1234&groupId=1&departmentId=1";
        var result =  request.post("/auth/register", form);
        assertThat(result.getStatusCode().value(), equalTo(200));
        assertThat(result.getBody(), containsString("User created"));
    }
}
