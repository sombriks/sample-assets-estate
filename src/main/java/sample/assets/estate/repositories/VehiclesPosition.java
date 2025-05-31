package sample.assets.estate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.assets.estate.models.VehiclePosition;

public interface VehiclesPosition extends JpaRepository<VehiclePosition, Long> {
}
