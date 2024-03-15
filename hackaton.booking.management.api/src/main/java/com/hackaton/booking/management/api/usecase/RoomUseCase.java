package com.hackaton.booking.management.api.usecase;

import static java.lang.String.format;

import com.hackaton.booking.management.api.domain.model.Bathroom;
import com.hackaton.booking.management.api.domain.model.Furniture;
import com.hackaton.booking.management.api.domain.model.Room;
import com.hackaton.booking.management.api.exceptions.NotFoundException;
import com.hackaton.booking.management.api.repository.RoomRepository;
import com.hackaton.booking.management.api.service.BathroomService;
import com.hackaton.booking.management.api.service.BuildingService;
import com.hackaton.booking.management.api.service.FurnitureService;
import com.hackaton.booking.management.api.service.RoomService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
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
            throw new NotFoundException(format("Bathroom type %s not found", requestedBathroom));
        }
        var newRoom = super.save(room);
        saveFurniture(furniture, newRoom.getId());
        return newRoom;
    }

    public Room update(Room updateRoom, List<Furniture> furniture, Long idRoom) {
        log.info(format("Updating Room ID %d", idRoom));
        validateIdBuilding(updateRoom.getIdBuilding());
        furnitureService.updateAll(furniture, idRoom);
        return super.update(updateRoom, idRoom);
    }

    public void deleteFurnitureById(Long id) {
        furnitureService.delete(id);
    }

    public Bathroom findBathroomByType(String type) {
        if (type != null) {
            var optBath = bathroomService.findByType(type);
            if (optBath.isEmpty()) {
                throw new NotFoundException(format("Bathroom type %s not found", type));
            }
            return optBath.get();
        }
        return null;
    }

    public List<Furniture> findFurnitureByIdRoom(Long idRoom) {
        return furnitureService.findByIdRoom(idRoom);
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
