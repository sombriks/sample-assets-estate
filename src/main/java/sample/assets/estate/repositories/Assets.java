package sample.assets.estate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.assets.estate.models.Asset;

public interface Assets extends JpaRepository<Asset, Long> {
}
