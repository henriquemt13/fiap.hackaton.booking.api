package com.hackaton.booking.management.api.usecase;

import com.hackaton.booking.management.api.domain.model.Building;
import com.hackaton.booking.management.api.exceptions.NotFoundException;
import com.hackaton.booking.management.api.repository.BuildingRepository;
import com.hackaton.booking.management.api.service.BuildingService;
import com.hackaton.booking.management.api.service.LocationService;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
public class BuildingUseCase extends BuildingService {

    private final LocationService locationService;

    public BuildingUseCase(BuildingRepository repository, LocationService locationService) {
        super(repository);
        this.locationService = locationService;
    }

    @Override
    public Building save(Building building) {
        validateIdLocation(building.getIdLocation());
        return super.save(building);
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
