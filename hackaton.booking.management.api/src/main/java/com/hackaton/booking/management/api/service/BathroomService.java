package com.hackaton.booking.management.api.service;

import com.hackaton.booking.management.api.domain.enums.BathroomTypeEnum;
import com.hackaton.booking.management.api.domain.model.Bathroom;
import com.hackaton.booking.management.api.exceptions.NotFoundException;
import com.hackaton.booking.management.api.repository.BathroomRepository;
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

    public Bathroom save(Bathroom bathroom) {
        return repository.save(bathroom);
    }

    public Bathroom update(Bathroom updateBathroom, Long id) {
        var currentBathroom = findValidBathroom(id);
        updateBathroom.setId(currentBathroom.getId());
        return repository.save(updateBathroom);
    }

    public void delete(Long id) {
        repository.delete(findValidBathroom(id));
    }

    public Optional<Bathroom> findById(Long id) {
        return repository.findById(id);
    }

    public Optional<Bathroom> findByType(String type) {
        return repository.findByType(type);
    }

    public List<Bathroom> findAll() {
        return repository.findAll();
    }

    private Bathroom findValidBathroom(Long id) {
        var optBathroom = repository.findById(id);
        if (optBathroom.isEmpty()) {
            throw new NotFoundException(format("Bathroom ID %d not found", id));
        }
        return optBathroom.get();
    }
}
