package sample.assets.estate.service;

import org.springframework.stereotype.Service;

@Service
public class AccessService {
    public boolean valid(String token) {
        return token != null;
    }
}
