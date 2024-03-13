package com.hackaton.booking.management.api.repository;

import com.hackaton.booking.management.api.domain.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
