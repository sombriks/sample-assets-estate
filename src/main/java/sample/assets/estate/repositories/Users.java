package sample.assets.estate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.assets.estate.models.User;

import java.util.List;

public interface Users extends JpaRepository<User, Long> {
    List<User> findByNameContainsIgnoreCaseOrderByName(String name);
}
