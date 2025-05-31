package sample.assets.estate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.assets.estate.models.FurniturePosition;

public interface FurnituresPosition extends JpaRepository<FurniturePosition, Long> {
}
