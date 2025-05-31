package sample.assets.estate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.assets.estate.models.Login;

public interface Logins extends JpaRepository<Login, Long> {
}
