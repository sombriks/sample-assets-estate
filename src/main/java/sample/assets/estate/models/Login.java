package sample.assets.estate.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "logins")
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "login_types_id")
    private LoginType loginType;
    @ManyToOne
    @JoinColumn(name = "users_id")
    private User  user;
    private String email;
    private String challenge;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime expires;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime created;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getChallenge() {
        return challenge;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    public LocalDateTime getExpires() {
        return expires;
    }

    public void setExpires(LocalDateTime expires) {
        this.expires = expires;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }
}
