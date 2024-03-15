package com.hackaton.booking.api.repository;

import com.hackaton.booking.api.domain.model.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AmenityRepository extends JpaRepository<Amenity, Long> {

    List<Amenity> findByIdLocation(Long idLocation);
}
