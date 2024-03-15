package com.hackaton.booking.management.api.service;

import com.hackaton.booking.management.api.domain.model.AddOn;
import com.hackaton.booking.management.api.exceptions.NotFoundException;
import com.hackaton.booking.management.api.repository.AddOnRepository;
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
public class AddOnService {

    private final AddOnRepository repository;

    public AddOn save(AddOn addOn) {
        return repository.save(addOn);
    }

    public AddOn update(Long id, AddOn updateAddOn) {
        var currentAddOn = validateId(id);
        updateAddOn.setId(currentAddOn.getId());
        updateAddOn.setCreatedAt(currentAddOn.getCreatedAt());
        updateAddOn.setUpdatedAt(OffsetDateTime.now());
        return repository.save(updateAddOn);
    }

    public Optional<AddOn> findById(Long id) {
        return repository.findById(id);
    }

    public List<AddOn> findAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.delete(validateId(id));
    }

    private AddOn validateId(Long id) {
        var optAddOn = findById(id);
        if (optAddOn.isEmpty()) {
            throw new NotFoundException(format("AddOn ID %d not found", id));
        }
        return optAddOn.get();
    }
}
