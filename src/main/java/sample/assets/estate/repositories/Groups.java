package sample.assets.estate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.assets.estate.models.Group;

public interface Groups extends JpaRepository<Group, Long> {
}
