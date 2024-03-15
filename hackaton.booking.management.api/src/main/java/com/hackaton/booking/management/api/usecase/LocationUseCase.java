package com.hackaton.booking.management.api.usecase;

import com.hackaton.booking.management.api.domain.model.Amenity;
import com.hackaton.booking.management.api.domain.model.Building;
import com.hackaton.booking.management.api.domain.model.Location;
import com.hackaton.booking.management.api.repository.LocationRepository;
import com.hackaton.booking.management.api.service.AmenityService;
import com.hackaton.booking.management.api.service.BuildingService;
import com.hackaton.booking.management.api.service.LocationService;
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

    public Location save(Location location, List<Amenity> amenities) {
        var newLocation = super.save(location);
        saveAmenities(amenities, newLocation.getId());
        return newLocation;
    }

    public Location update(Location location, List<Amenity> amenities, Long id) {
        amenities.forEach(amenity -> amenity.setIdLocation(id));
        amenityService.updateAll(amenities, id);
        return super.update(id, location);
    }

    public void deleteAmenityById(Long id) {
        amenityService.delete(id);
    }

    @Override
    public void delete(Long id){
        amenityService.deleteByIdLocation(id);
        super.delete(id);
    }

    public List<Amenity> findAmenities(Long idLocation) {
        return amenityService.findByIdLocation(idLocation);
    }

    public List<Building> findBuildingsByIdLocation(Long idLocation) {
        return buildingService.findByIdLocation(idLocation);
    }

    private void saveAmenities(List<Amenity> amenities, Long idLocation) {
        amenities.forEach(amenity -> amenity.setIdLocation(idLocation));
        amenityService.saveAll(amenities);
    }
}
