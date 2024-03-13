package com.hackaton.booking.management.api.service;

import com.hackaton.booking.management.api.domain.model.Amenity;
import com.hackaton.booking.management.api.exceptions.NotFoundException;
import com.hackaton.booking.management.api.repository.AmenityRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class AmenityService {

    private final AmenityRepository repository;

    public void saveAll(List<Amenity> amenities) {
        repository.saveAll(amenities);
    }

    public List<Amenity> updateAll(List<Amenity> updateAmenities) {
        updateAmenities.forEach(amenity -> findValidAmenity(amenity.getId()));
        return repository.saveAll(updateAmenities);
    }

    public void delete(Long id) {
        repository.delete(findValidAmenity(id));
    }

    public void deleteByIdLocation(Long idLocation) {
        repository.deleteByIdLocation(idLocation);
    }

    public Optional<Amenity> findById(Long id) {
        return repository.findById(id);
    }

    public List<Amenity> findAll() {
        return repository.findAll();
    }

    private Amenity findValidAmenity(Long id) {
        var optAmenity = repository.findById(id);
        if (optAmenity.isEmpty()) {
            throw new NotFoundException(format("Amenity ID %d not found", id));
        }
        return optAmenity.get();
    }
}
