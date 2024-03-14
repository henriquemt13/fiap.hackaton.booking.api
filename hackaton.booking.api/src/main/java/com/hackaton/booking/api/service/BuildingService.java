package com.hackaton.booking.api.service;

import com.hackaton.booking.api.domain.model.Building;
import com.hackaton.booking.api.repository.BuildingRepository;
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

    public Optional<Building> findById(Long id) {
        return repository.findById(id);
    }

    public List<Building> findByIdLocation(Long idLocation) {
        return repository.findByIdLocation(idLocation);
    }

    public List<Building> findAll() {
        return repository.findAll();
    }

}
