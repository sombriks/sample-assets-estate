package sample.assets.estate.dtos;

import jakarta.validation.constraints.*;
import sample.assets.estate.models.Login;
import sample.assets.estate.models.User;

import java.time.LocalDateTime;

public class RegisterDTO {
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 4)
    private String password;
    @NotNull
    @Min(0)
    private Long groupId;
    @NotNull
    @Min(0)
    private Long departmentId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public User fill(User user) {
        user.setName(this.name);
        user.setUpdated(LocalDateTime.now());
        return user;
    }

    public Login fill(Login login) {
        login.setEmail(this.email);
        login.setChallenge(this.password);
        login.setUpdated(LocalDateTime.now());
        return login;
    }
}
