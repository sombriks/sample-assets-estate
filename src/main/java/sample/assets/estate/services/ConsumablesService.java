package sample.assets.estate.services;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sample.assets.estate.dtos.CreateConsumableDTO;
import sample.assets.estate.dtos.UpdateConsumableDTO;
import sample.assets.estate.models.Asset;
import sample.assets.estate.models.ConsumablePosition;
import sample.assets.estate.models.User;
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

    public ConsumablePosition newConsumable(User user, CreateConsumableDTO form) {
        Asset asset = form.fill(new Asset());
        assetsRepository.save(asset);
        ConsumablePosition consumablePosition = form.fill(new ConsumablePosition());
        consumablePosition.setAsset(asset);
        consumablePosition.setAuthor(user);
        repository.save(consumablePosition);
        return consumablePosition;
    }

    public ConsumablePosition updateConsumable(User user, UpdateConsumableDTO form) {
        ConsumablePosition consumable = repository.findById(form.getId()).orElse(null);
        if (consumable == null) return null;
        Asset asset = form.fill(consumable.getAsset());
        assetsRepository.save(asset);
        // new consumable version
        ConsumablePosition newConsumable = form.fill(new ConsumablePosition());
        newConsumable.setAuthor(user);
        newConsumable.setAsset(consumable.getAsset());
        newConsumable = repository.save(newConsumable);
        return newConsumable;
    }
}
