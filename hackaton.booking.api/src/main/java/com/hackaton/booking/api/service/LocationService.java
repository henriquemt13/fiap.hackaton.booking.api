package com.hackaton.booking.api.service;

import com.hackaton.booking.api.specification.SearchLocationSpecification;
import com.hackaton.booking.api.domain.model.Location;
import com.hackaton.booking.api.repository.LocationRepository;
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
public class LocationService {

    private final LocationRepository repository;

    public Optional<Location> findById(Long id) {
        return repository.findById(id);
    }

    public List<Location> findAll(Location request) {
        return repository.findAll(SearchLocationSpecification.search(request));
    }
    public List<Location> findByNameContains(String name) {
        return repository.findByNameContains(name);
    }


}
