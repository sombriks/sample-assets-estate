package sample.assets.estate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.assets.estate.models.AssetStatus;

public interface AssetStatuses extends JpaRepository<AssetStatus, Long> {
}
