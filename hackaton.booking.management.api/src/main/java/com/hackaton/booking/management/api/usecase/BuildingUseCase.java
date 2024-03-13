package com.hackaton.booking.management.api.usecase;

import com.hackaton.booking.management.api.domain.model.Building;
import com.hackaton.booking.management.api.domain.model.Location;
import com.hackaton.booking.management.api.domain.model.Room;
import com.hackaton.booking.management.api.exceptions.NotFoundException;
import com.hackaton.booking.management.api.repository.BuildingRepository;
import com.hackaton.booking.management.api.service.BuildingService;
import com.hackaton.booking.management.api.service.LocationService;
import com.hackaton.booking.management.api.service.RoomService;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.lang.String.format;

@Component
public class BuildingUseCase extends BuildingService {

    private final LocationService locationService;

    private final RoomService roomService;

    public BuildingUseCase(BuildingRepository repository, LocationService locationService, RoomService roomService) {
        super(repository);
        this.locationService = locationService;
        this.roomService = roomService;
    }

    @Override
    public Building save(Building building) {
        validateIdLocation(building.getIdLocation());
        return super.save(building);
    }

    public List<Room> findRoomsByIdBuilding(Long id) {
        return roomService.findByIdBuilding(id);
    }

    @Override
    public Building update(Building building, Long id) {
        validateIdLocation(building.getIdLocation());
        return super.update(building, id);
    }

    private void validateIdLocation(Long id) {
        if (locationService.findById(id).isEmpty()) {
            throw new NotFoundException(format("Location ID %d not found", id));
        }

    }
}
