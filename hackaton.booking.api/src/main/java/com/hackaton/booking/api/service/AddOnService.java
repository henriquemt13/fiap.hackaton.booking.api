package com.hackaton.booking.api.service;

import com.hackaton.booking.api.domain.model.AddOn;
import com.hackaton.booking.api.repository.AddOnRepository;
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
public class AddOnService {

    private final AddOnRepository repository;

    public Optional<AddOn> findById(Long id) {
        return repository.findById(id);
    }

    public List<AddOn> findByIdIn(List<Long> ids) {
        return repository.findByIdIn(ids);
    }

    public List<AddOn> findAll() {
        return repository.findAll();
    }

}
