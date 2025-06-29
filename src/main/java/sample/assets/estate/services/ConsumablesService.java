package sample.assets.estate.services;

import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sample.assets.estate.dtos.ConsumableDTO;
import sample.assets.estate.models.*;
import sample.assets.estate.repositories.Assets;
import sample.assets.estate.repositories.ConsumablesPosition;

import java.util.List;

@Service
public class ConsumablesService {

    private final Assets assetsRepository;
    private final ConsumablesPosition repository;

    public ConsumablesService(Assets assetsRepository, ConsumablesPosition repository) {
        this.assetsRepository = assetsRepository;
        this.repository = repository;
    }

    public List<ConsumablePosition> listConsumables(String q, User user, Sort sort) {
        return repository.findLatestPosition(q, user, sort);
    }

    public ConsumablePosition newConsumable(User user, ConsumableDTO form) {
        Asset asset = form.fill(new Asset());
        assetsRepository.save(asset);
        ConsumablePosition consumablePosition = form.fill(new ConsumablePosition());
        consumablePosition.setAsset(asset);
        consumablePosition.setAuthor(user);
        consumablePosition.setChangeReason(ChangeReason.INCLUSION);
        consumablePosition.setAssetStatus(AssetStatus.AVAILABLE);
        repository.save(consumablePosition);
        return consumablePosition;
    }

    public ConsumablePosition updateConsumable(User user, @Valid ConsumableDTO form) {
        ConsumablePosition consumable = repository.findById(form.getId()).orElse(null);
        if (consumable == null)
            return null;
        // new consumable version
        ConsumablePosition newConsumable = form.fill(new ConsumablePosition());
        newConsumable.setAuthor(user);
        newConsumable.setAsset(consumable.getAsset());
        newConsumable = repository.save(newConsumable);
        return newConsumable;
    }
}
