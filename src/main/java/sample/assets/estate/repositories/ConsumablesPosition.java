package sample.assets.estate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.assets.estate.models.ConsumablePosition;

public interface ConsumablesPosition extends JpaRepository<ConsumablePosition, Long> {
}
