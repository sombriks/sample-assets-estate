package sample.assets.estate.endpoints;

import org.junit.jupiter.api.Disabled;
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
public class MenuTest {

    @Autowired
    private BaseRequest request;

    @Test
//    @Disabled("needs a better test security filter")
    public void shouldRenderMenu() {
        var result = request.getWithAuth("/menu");
        assertThat(result.getStatusCode().value(), equalTo(200));
        assertThat(result.getBody(), containsString("Dashboard"));
    }

    @Test
    public void shouldNotGetMenu() {
        var result = request.get("/menu");
        assertThat(401, equalTo(result.getStatusCode().value()));
        assertThat(result.getBody(), containsString("Unauthorized"));
    }
}
