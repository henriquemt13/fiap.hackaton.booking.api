package com.hackaton.booking.management.api.usecase;

import com.hackaton.booking.management.api.domain.model.Amenity;
import com.hackaton.booking.management.api.domain.model.Location;
import com.hackaton.booking.management.api.repository.LocationRepository;
import com.hackaton.booking.management.api.service.AmenityService;
import com.hackaton.booking.management.api.service.LocationService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LocationUseCase extends LocationService {

    private final AmenityService amenityService;

    public LocationUseCase(LocationRepository repository, AmenityService amenityService) {
        super(repository);
        this.amenityService = amenityService;
    }

    public Location save(Location location, List<Amenity> amenities) {
        var newLocation = super.save(location);
        saveAmenities(amenities, newLocation.getId());
        return newLocation;
    }

    public Location update(Location location, List<Amenity> amenities, Long id) {
        amenityService.updateAll(amenities);
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

    private void saveAmenities(List<Amenity> amenities, Long idLocation) {
        amenities.forEach(amenity -> amenity.setIdLocation(idLocation));
        amenityService.saveAll(amenities);
    }
}
