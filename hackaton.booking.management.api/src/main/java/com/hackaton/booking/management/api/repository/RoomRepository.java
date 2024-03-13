package com.hackaton.booking.management.api.repository;

import com.hackaton.booking.management.api.domain.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByIdBuilding(Long id);
}
