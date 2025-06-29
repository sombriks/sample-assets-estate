package sample.assets.estate.configurations;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import sample.assets.estate.models.Login;
import sample.assets.estate.repositories.Logins;

import java.util.Base64;

public class BaseRequest {

    private final TestRestTemplate restTemplate;

    public BaseRequest(TestRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> get(String uri) {
        return request(HttpMethod.GET, uri, null);
    }

    public ResponseEntity<String> getWithAuth(String uri) {
        String auth = Base64.getEncoder().encodeToString("test@test.com:1234".getBytes());
        return request(HttpMethod.GET, uri, auth);
    }

    private ResponseEntity<String> request(HttpMethod method, String uri, String auth, String ...bodyParams) {
        HttpHeaders headers = new HttpHeaders();
        if (auth != null) headers.setBasicAuth(auth);
        return restTemplate.exchange(uri, method,
                new HttpEntity<>(headers), String.class);
    }
}
