package com.hackaton.booking.management.api.service;

import com.hackaton.booking.management.api.domain.model.Room;
import com.hackaton.booking.management.api.exceptions.NotFoundException;
import com.hackaton.booking.management.api.repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class RoomService {

    private final RoomRepository repository;

    public Room save(Room room) {
        return repository.save(room);
    }

    public Room update(Room updateRoom, Long id) {
        var currentRoom = findValidRoom(id);
        updateRoom.setId(currentRoom.getId());
        return repository.save(updateRoom);
    }

    public void delete(Long id) {
        repository.delete(findValidRoom(id));
    }

    public Optional<Room> findById(Long id) {
        return repository.findById(id);
    }

    public List<Room> findAll() {
        return repository.findAll();
    }

    private Room findValidRoom(Long id) {
        var optRoom = repository.findById(id);
        if (optRoom.isEmpty()) {
            throw new NotFoundException(format("Room ID %d not found", id));
        }
        return optRoom.get();
    }
}
