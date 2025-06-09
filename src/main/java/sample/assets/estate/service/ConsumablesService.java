package sample.assets.estate.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sample.assets.estate.models.ConsumablePosition;
import sample.assets.estate.models.User;
import sample.assets.estate.repositories.ConsumablesPosition;

import java.util.List;

@Service
public class ConsumablesService {

    private final ConsumablesPosition repository;

    public ConsumablesService(ConsumablesPosition repository) {
        this.repository = repository;
    }

    public List<ConsumablePosition> listConsumables(String q, User user, Sort sort) {
        return repository.findAll(sort);
    }
}
