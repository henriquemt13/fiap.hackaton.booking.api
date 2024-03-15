package com.hackaton.booking.api.service;

import com.hackaton.booking.api.repository.BathroomRepository;
import com.hackaton.booking.api.domain.model.Bathroom;
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
public class BathroomService {

    private final BathroomRepository repository;

    public Optional<Bathroom> findById(Long id) {
        return repository.findById(id);
    }

    public Optional<Bathroom> findByType(String type) {
        return repository.findByType(type);
    }

    public List<Bathroom> findAll() {
        return repository.findAll();
    }

}
