package com.hackaton.booking.management.api.repository;

import com.hackaton.booking.management.api.domain.enums.BathroomTypeEnum;
import com.hackaton.booking.management.api.domain.model.Bathroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BathroomRepository extends JpaRepository<Bathroom, Long> {

    Optional<Bathroom> findByType(BathroomTypeEnum type);
}
