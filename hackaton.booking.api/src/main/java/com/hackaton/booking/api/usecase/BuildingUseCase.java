package com.hackaton.booking.api.usecase;

import com.hackaton.booking.api.domain.model.Room;
import com.hackaton.booking.api.repository.BuildingRepository;
import com.hackaton.booking.api.service.BuildingService;
import com.hackaton.booking.api.service.RoomService;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.lang.String.format;

@Component
public class BuildingUseCase extends BuildingService {

    private final RoomService roomService;

    public BuildingUseCase(BuildingRepository repository, RoomService roomService) {
        super(repository);
        this.roomService = roomService;
    }

    public List<Room> findRoomsByIdBuilding(Long id) {
        return roomService.findByIdBuilding(id);
    }

}

