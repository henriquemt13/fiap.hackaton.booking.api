package com.hackaton.booking.management.api.repository;

import com.hackaton.booking.management.api.domain.model.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AmenityRepository extends JpaRepository<Amenity, Long> {

    void deleteByIdLocation(Long idLocation);

    List<Amenity> findByIdLocation(Long idLocation);
}
