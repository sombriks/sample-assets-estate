package sample.assets.estate.endpoints.base;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import sample.assets.estate.models.User;
import sample.assets.estate.service.AccessService;

import java.util.Arrays;
import java.util.List;

public abstract class BaseEP {

    private final AccessService accessService;
    private final List<String> permissions;

    public BaseEP(AccessService accessService, String  ... permissions) {
        this.accessService = accessService;
        this.permissions = Arrays.asList(permissions);
    }

    protected User checkPermission(String token) {
        User user = accessService.findUser(token);
        if (user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        if (user.getGroups().stream().noneMatch(g -> permissions.contains(g.getName()))) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User not allowed to list users");
        }
        return user;
    }
}
