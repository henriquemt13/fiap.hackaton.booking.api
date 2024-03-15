package com.hackaton.booking.api.repository;

import com.hackaton.booking.api.domain.model.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FurnitureRepository extends JpaRepository<Furniture, Long> {

    List<Furniture> findByIdRoom(Long idRoom);
}
