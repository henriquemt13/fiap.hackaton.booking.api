package com.hackaton.booking.management.api.service;

import com.hackaton.booking.management.api.domain.model.Location;
import com.hackaton.booking.management.api.exceptions.NotFoundException;
import com.hackaton.booking.management.api.repository.LocationRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class LocationService {

    private final LocationRepository repository;

    public Location save(Location location) {
        return repository.save(location);
    }

    public Location update(Long id, Location updateLocation) {
        var currentLocation = validateId(id);
        updateLocation.setId(currentLocation.getId());
        updateLocation.setCreatedAt(currentLocation.getCreatedAt());
        updateLocation.setUpdatedAt(OffsetDateTime.now());
        return repository.save(updateLocation);
    }

    public Optional<Location> findById(Long id) {
        return repository.findById(id);
    }

    public List<Location> findAll() {
        return repository.findAll();
    }

    public List<Location> findByNameContains(String name) {
        return repository.findByNameContains(name);
    }

    public void delete(Long id) {
        repository.delete(validateId(id));
    }

    private Location validateId(Long id) {
        var optLocation = findById(id);
        if (optLocation.isEmpty()) {
            throw new NotFoundException(format("Location ID %d not found", id));
        }
        return optLocation.get();
    }
}
