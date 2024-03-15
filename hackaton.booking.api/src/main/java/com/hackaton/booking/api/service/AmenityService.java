package com.hackaton.booking.api.service;

import com.hackaton.booking.api.repository.AmenityRepository;
import com.hackaton.booking.api.domain.model.Amenity;
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

    public Optional<Amenity> findById(Long id) {
        return repository.findById(id);
    }

    public List<Amenity> findAll() {
        return repository.findAll();
    }

    public List<Amenity> findByIdLocation(Long idLocation) {
        return repository.findByIdLocation(idLocation);
    }

}
