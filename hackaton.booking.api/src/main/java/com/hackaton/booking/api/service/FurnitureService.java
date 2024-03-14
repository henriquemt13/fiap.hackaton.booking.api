package com.hackaton.booking.api.service;

import com.hackaton.booking.api.repository.FurnitureRepository;
import com.hackaton.booking.api.domain.model.Furniture;
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
public class FurnitureService {

    private final FurnitureRepository repository;

    public Optional<Furniture> findById(Long id) {
        return repository.findById(id);
    }

    public List<Furniture> findAll() {
        return repository.findAll();
    }

    public List<Furniture> findByIdRoom(Long idRoom) {
        return repository.findByIdRoom(idRoom);
    }

}
