package sample.assets.estate.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sample.assets.estate.dto.ConsumableDTO;
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
        return repository.findAll(sort);
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
}
