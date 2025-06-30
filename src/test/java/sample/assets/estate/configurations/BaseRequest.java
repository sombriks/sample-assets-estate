package sample.assets.estate.configurations;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import sample.assets.estate.models.Login;
import sample.assets.estate.repositories.Logins;

import java.util.Base64;

public class BaseRequest {

    private final TestRestTemplate restTemplate;
    private final String auth;

    public BaseRequest(TestRestTemplate restTemplate, String testCredentials) {
        this.restTemplate = restTemplate;
        auth = Base64.getEncoder() //
                .encodeToString(testCredentials.getBytes());

    }

    public ResponseEntity<String> get(String uri) {
        return request(HttpMethod.GET, uri, null, null);
    }

    public ResponseEntity<String> getWithAuth(String uri) {
        return request(HttpMethod.GET, uri, auth, null);
    }

    public ResponseEntity<String> post(String uri, String body) {
        return request(HttpMethod.POST, uri, null, body);
    }

    public ResponseEntity<String> postWithAuth(String uri, String body) {
        return request(HttpMethod.POST, uri, auth, body);
    }

    public ResponseEntity<String> putWithAuth(String uri, String body) {
        return request(HttpMethod.PUT, uri, auth, body);
    }

    private ResponseEntity<String> request(HttpMethod method, String uri, String auth, String bodyParams) {
        HttpHeaders headers = new HttpHeaders();
        if (auth != null) headers.setBasicAuth(auth);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return restTemplate.exchange(uri, method,
                new HttpEntity<>(bodyParams, headers), String.class);
    }
}
