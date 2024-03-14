package com.hackaton.booking.api.service;

import com.hackaton.booking.api.repository.RoomRepository;
import com.hackaton.booking.api.domain.model.Room;
import com.hackaton.booking.api.specification.SearchRoomSpecification;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class RoomService {

    private final RoomRepository repository;

    public Optional<Room> findById(Long id) {
        return repository.findById(id);
    }

    public List<Room> findByIdBuilding(Long id) {
        return repository.findByIdBuilding(id);
    }

    public List<Room> findAll(Room request) {
        return repository.findAll(SearchRoomSpecification.search(request));
    }

}
