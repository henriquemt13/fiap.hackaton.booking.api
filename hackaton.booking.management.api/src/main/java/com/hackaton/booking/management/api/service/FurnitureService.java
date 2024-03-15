package com.hackaton.booking.management.api.service;

import com.hackaton.booking.management.api.domain.model.Furniture;
import com.hackaton.booking.management.api.exceptions.NotFoundException;
import com.hackaton.booking.management.api.repository.FurnitureRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class FurnitureService {

    private final FurnitureRepository repository;

    public void saveAll(List<Furniture> furniture) {
        repository.saveAll(furniture);
    }

    public void updateAll(List<Furniture> updateFurniture, Long idRoom) {
        updateFurniture.forEach(f -> {
            var current = f.getId() != null ? findValidFurniture(f.getId()) : null;
            if (current != null) {
                f.setCreatedAt(current.getCreatedAt());
                f.setUpdatedAt(OffsetDateTime.now());
                f.setIdRoom(current.getIdRoom());
            }
            f.setIdRoom(idRoom);
        });
        repository.saveAll(updateFurniture);
    }

    public void delete(Long id) {
        repository.delete(findValidFurniture(id));
    }

    public void deleteByIdRoom(Long id) {
        repository.deleteByIdRoom(id);
    }

    public Optional<Furniture> findById(Long id) {
        return repository.findById(id);
    }

    public List<Furniture> findAll() {
        return repository.findAll();
    }

    public List<Furniture> findByIdRoom(Long idRoom) {
        return repository.findByIdRoom(idRoom);
    }


    private Furniture findValidFurniture(Long id) {
        var optFurniture = repository.findById(id);
        if (optFurniture.isEmpty()) {
            throw new NotFoundException(format("Furniture ID %d not found", id));
        }
        return optFurniture.get();
    }
}
