package com.hackaton.booking.management.api.repository;

import com.hackaton.booking.management.api.domain.model.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FurnitureRepository extends JpaRepository<Furniture, Long> {

    void deleteByIdRoom(Long idRoom);
}
