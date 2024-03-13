package com.hackaton.booking.management.api.service;

import com.hackaton.booking.management.api.domain.model.Building;
import com.hackaton.booking.management.api.exceptions.NotFoundException;
import com.hackaton.booking.management.api.repository.BuildingRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
@AllArgsConstructor
@Transactional
public class BuildingService {

    private final BuildingRepository repository;

    public Building save(Building building) {
        return repository.save(building);
    }

    public Building update(Building updateBuilding, Long id) {
        var currentBuilding = findValidBuilding(id);
        updateBuilding.setId(currentBuilding.getId());
        return repository.save(updateBuilding);
    }

    public void delete(Long id) {
        repository.delete(findValidBuilding(id));
    }

    public Optional<Building> findById(Long id) {
        return repository.findById(id);
    }

    public List<Building> findAll() {
        return repository.findAll();
    }

    private Building findValidBuilding(Long id) {
        var optBuilding = repository.findById(id);
        if (optBuilding.isEmpty()) {
            throw new NotFoundException(format("Building ID %d not found", id));
        }
        return optBuilding.get();
    }
}
