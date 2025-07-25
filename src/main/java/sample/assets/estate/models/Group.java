package sample.assets.estate.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;

@Entity
@Table(name="groups")
public class Group implements GrantedAuthority {
    @Id
    private Long id;
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime created;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updated;

    @Override
    public String getAuthority() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
