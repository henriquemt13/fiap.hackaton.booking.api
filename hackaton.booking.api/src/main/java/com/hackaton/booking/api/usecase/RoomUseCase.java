package com.hackaton.booking.api.usecase;

import com.hackaton.booking.api.exceptions.NotFoundException;
import com.hackaton.booking.api.domain.model.Bathroom;
import com.hackaton.booking.api.domain.model.Furniture;
import com.hackaton.booking.api.repository.RoomRepository;
import com.hackaton.booking.api.service.BathroomService;
import com.hackaton.booking.api.service.FurnitureService;
import com.hackaton.booking.api.service.RoomService;
import org.springframework.stereotype.Component;
import java.util.List;

import static java.lang.String.format;

@Component
public class RoomUseCase extends RoomService {

    private final BathroomService bathroomService;
    private final FurnitureService furnitureService;

    public RoomUseCase(RoomRepository repository, BathroomService bathroomService,
                       FurnitureService furnitureService) {
        super(repository);
        this.bathroomService = bathroomService;
        this.furnitureService = furnitureService;
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


}
