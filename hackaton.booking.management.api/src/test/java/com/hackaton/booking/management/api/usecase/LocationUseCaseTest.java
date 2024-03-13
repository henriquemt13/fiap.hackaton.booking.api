package com.hackaton.booking.management.api.usecase;

import com.hackaton.booking.management.api.exceptions.NotFoundException;
import com.hackaton.booking.management.api.fixture.AmenityFixture;
import com.hackaton.booking.management.api.fixture.LocationFixture;
import com.hackaton.booking.management.api.repository.LocationRepository;
import com.hackaton.booking.management.api.service.AmenityService;
import com.hackaton.booking.management.api.service.LocationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LocationUseCaseTest {

    @InjectMocks
    private LocationUseCase useCase;

    @Mock
    private LocationService service;

    @Mock
    private AmenityService amenityService;

    @Mock
    private LocationRepository repository;


    @Nested
    @DisplayName("1. Save Locations")
    class SaveLocations {

        @Test
        @DisplayName("1.1 Should save All Locations")
        void shouldSaveLocation() {
            when(repository.save(any())).thenReturn(LocationFixture.buildLocation());
            doNothing().when(amenityService).saveAll(any());

            assertDoesNotThrow(() -> useCase.save(LocationFixture.buildLocation(), AmenityFixture.buildAmenities()));
        }

    }

    @Nested
    @DisplayName("2. Update Locations")
    class UpdateLocations {

        @Test
        @DisplayName("2.1 Should Update Location")
        void shouldUpdateLocation() {
            var updateResponse = LocationFixture.buildLocation();
            updateResponse.setName("Test3");

            when(repository.findById(anyLong())).thenReturn(Optional.of(LocationFixture.buildLocation()));
            when(repository.save(any())).thenReturn(updateResponse);

            var location = useCase.update(updateResponse, AmenityFixture.buildAmenities(), 1L);

            verify(repository, times(1)).save(any());
            verify(repository, times(1)).findById(anyLong());
            assertEquals(updateResponse, location);
        }

    }

    @Nested
    @DisplayName("3. Delete Locations and Amenities")
    class DeleteLocationsAndAmenities {

        @Test
        @DisplayName("3.1 Should delete Location and all its Amenities")
        void shouldDeleteLocationAndAllItsAmenities() {
            when(repository.findById(anyLong())).thenReturn(Optional.of(LocationFixture.buildLocation()));
            doNothing().when(amenityService).deleteByIdLocation(anyLong());
            assertDoesNotThrow(() -> useCase.delete(1L));
        }

        @Test
        @DisplayName("3.2 Should Delete Amenity by ID")
        void shouldDeleteLocation() {
            doNothing().when(amenityService).delete(any());
            assertDoesNotThrow(() -> useCase.deleteAmenityById(1L));
        }


    }

}