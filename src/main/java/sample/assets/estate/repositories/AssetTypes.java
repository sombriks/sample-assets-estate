package sample.assets.estate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.assets.estate.models.AssetType;

public interface AssetTypes extends JpaRepository<AssetType, Long> {
}
