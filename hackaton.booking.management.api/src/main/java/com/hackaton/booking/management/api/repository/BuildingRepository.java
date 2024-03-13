package com.hackaton.booking.management.api.repository;

import com.hackaton.booking.management.api.domain.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<Building, Long> {


}
