package com.hackaton.booking.management.api.usecase;

import com.hackaton.booking.management.api.domain.model.Furniture;
import com.hackaton.booking.management.api.domain.model.Room;
import com.hackaton.booking.management.api.exceptions.NotFoundException;
import com.hackaton.booking.management.api.repository.RoomRepository;
import com.hackaton.booking.management.api.service.BathroomService;
import com.hackaton.booking.management.api.service.BuildingService;
import com.hackaton.booking.management.api.service.FurnitureService;
import com.hackaton.booking.management.api.service.RoomService;
import org.springframework.stereotype.Component;
import java.util.List;

import static java.lang.String.format;

@Component
public class RoomUseCase extends RoomService {

    private final BathroomService bathroomService;
    private final BuildingService buildingService;
    private final FurnitureService furnitureService;

    public RoomUseCase(RoomRepository repository, BuildingService buildingService, BathroomService bathroomService,
                       FurnitureService furnitureService) {
        super(repository);
        this.bathroomService = bathroomService;
        this.furnitureService = furnitureService;
        this.buildingService = buildingService;
    }

    public Room save(Room room, List<Furniture> furniture) {
        validateIdBuilding(room.getIdBuilding());
        var requestedBathroom = room.getBathroomType();
        if (requestedBathroom != null && bathroomService.findByType(requestedBathroom).isEmpty()) {
            throw new NotFoundException(format("Bathroom type %s not found", requestedBathroom.name()));
        }
        var newRoom = super.save(room);
        saveFurniture(furniture, newRoom.getId());
        return newRoom;
    }

    public Room update(Room updateRoom, List<Furniture> furniture, Long id) {
        validateIdBuilding(updateRoom.getIdBuilding());
        furnitureService.updateAll(furniture);
        return super.update(updateRoom, id);
    }

    public void deleteFurnitureById(Long id) {
        furnitureService.delete(id);
    }

    @Override
    public void delete(Long id) {
        furnitureService.deleteByIdRoom(id);
        super.delete(id);
    }

    private void saveFurniture(List<Furniture> furniture, Long idRoom) {
        furniture.forEach(f -> f.setIdRoom(idRoom));
        furnitureService.saveAll(furniture);

    }

    private void validateIdBuilding(Long id) {
        if (buildingService.findById(id).isEmpty()) {
            throw new NotFoundException(format("Building ID %d not found", id));
        }
    }

}
