package com.hackaton.booking.api.repository;

import com.hackaton.booking.api.domain.model.Bathroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BathroomRepository extends JpaRepository<Bathroom, Long> {
    Optional<Bathroom> findByType(String type);
}
