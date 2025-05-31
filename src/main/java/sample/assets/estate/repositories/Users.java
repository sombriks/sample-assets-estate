package sample.assets.estate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.assets.estate.models.User;

public interface Users extends JpaRepository<User, Long> {
}
