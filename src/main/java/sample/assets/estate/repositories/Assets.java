package sample.assets.estate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.assets.estate.models.Asset;
import sample.assets.estate.models.AssetType;

import java.util.List;

public interface Assets extends JpaRepository<Asset, Long> {
    List<Asset> findByType(AssetType type);
}
