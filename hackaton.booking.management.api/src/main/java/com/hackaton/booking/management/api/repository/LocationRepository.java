package com.hackaton.booking.management.api.repository;

import com.hackaton.booking.management.api.domain.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {

    List<Location> findByNameContains(String name);
}
