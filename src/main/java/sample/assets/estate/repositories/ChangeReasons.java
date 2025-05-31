package sample.assets.estate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.assets.estate.models.ChangeReason;

public interface ChangeReasons extends JpaRepository<ChangeReason, Long> {
}
