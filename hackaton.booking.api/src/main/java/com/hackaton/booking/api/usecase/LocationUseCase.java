package com.hackaton.booking.api.usecase;

import com.hackaton.booking.api.domain.model.Amenity;
import com.hackaton.booking.api.domain.model.Building;
import com.hackaton.booking.api.repository.LocationRepository;
import com.hackaton.booking.api.service.AmenityService;
import com.hackaton.booking.api.service.BuildingService;
import com.hackaton.booking.api.service.LocationService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LocationUseCase extends LocationService {

    private final AmenityService amenityService;
    private final BuildingService buildingService;

    public LocationUseCase(LocationRepository repository, AmenityService amenityService,
                           BuildingService buildingService) {
        super(repository);
        this.amenityService = amenityService;
        this.buildingService = buildingService;
    }

    public List<Amenity> findAmenities(Long idLocation) {
        return amenityService.findByIdLocation(idLocation);
    }

    public List<Building> findBuildingsByIdLocation(Long idLocation) {
        return buildingService.findByIdLocation(idLocation);
    }
}
