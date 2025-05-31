package sample.assets.estate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.assets.estate.models.BuildingPosition;

public interface BuildingsPosition extends JpaRepository<BuildingPosition, Long> {
}
