package com.hackaton.booking.management.api.service;

import com.hackaton.booking.management.api.domain.model.Amenity;
import com.hackaton.booking.management.api.exceptions.NotFoundException;
import com.hackaton.booking.management.api.fixture.AmenityFixture;
import com.hackaton.booking.management.api.repository.AmenityRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AmenityServiceTest {

    @InjectMocks
    private AmenityService service;

    @Mock
    private AmenityRepository repository;

    @Nested
    @DisplayName("1. Find Amenities")
    class FindAmenities {

        @Test
        @DisplayName("1.1 Should find All Amenities")
        void shouldFindAll() {
            var expectedResponse = AmenityFixture.buildAmenities();
            when(repository.findAll()).thenReturn(expectedResponse);

            var Amenities = service.findAll();

            verify(repository, times(1)).findAll();
            assertEquals(expectedResponse, Amenities);
        }

        @Test
        @DisplayName("1.2 Should find Amenity by Id")
        void shouldFindById() {
            var expectedResponse = Optional.of(AmenityFixture.buildAmenity());
            when(repository.findById(anyLong())).thenReturn(expectedResponse);

            var Amenity = service.findById(1L);

            verify(repository, times(1)).findById(anyLong());
            assertEquals(expectedResponse, Amenity);
        }

        @Test
        @DisplayName("1.3 Should not find Amenity for given Id")
        void shouldNotFindById() {
            when(repository.findById(anyLong())).thenReturn(Optional.empty());

            var Amenity = service.findById(1L);

            verify(repository, times(1)).findById(anyLong());
            assertEquals(Optional.empty(), Amenity);
        }
    }

    @Nested
    @DisplayName("2. Save Amenities")
    class SaveAmenities {

        @Test
        @DisplayName("2.1 Should save All Amenities")
        void shouldSaveAmenity() {
            when(repository.saveAll(any())).thenReturn(List.of(AmenityFixture.buildAmenity()));

            assertDoesNotThrow(() -> service.saveAll(AmenityFixture.buildAmenities()));
        }

    }

    @Nested
    @DisplayName("3. Update Amenities")
    class UpdateAmenities {

        @Test
        @DisplayName("3.1 Should Update Amenity")
        void shouldUpdateAmenity() {
            var updateResponse = AmenityFixture.buildAmenity();
            updateResponse.setName("Test3");

            when(repository.findById(anyLong())).thenReturn(Optional.of(AmenityFixture.buildAmenity()));
            when(repository.saveAll(any())).thenReturn(List.of(updateResponse));

            var amenities = service.updateAll(List.of(updateResponse), 1L);

            verify(repository, times(1)).saveAll(any());
            verify(repository, times(1)).findById(anyLong());
            assertEquals(List.of(updateResponse), amenities);
        }

        @Test
        @DisplayName("3.2 Should Throw When Trying to Update a Non-Existent Amenity")
        void shouldUThrowNotFoundExceptionWhenUpdateAmenity() {
            var updateResponse = AmenityFixture.buildAmenity();
            updateResponse.setName("Test3");

            when(repository.findById(anyLong())).thenReturn(Optional.empty());

            assertThrows(NotFoundException.class, () -> service.updateAll(List.of(updateResponse), 1L));
        }

    }

    @Nested
    @DisplayName("4. Delete Amenities")
    class DeleteAmenities {

        @Test
        @DisplayName("4.1 Should delete Amenity")
        void shouldDeleteAmenity() {
            when(repository.findById(anyLong())).thenReturn(Optional.of(AmenityFixture.buildAmenity()));
            doNothing().when(repository).delete(AmenityFixture.buildAmenity());
            assertDoesNotThrow(() -> service.delete(1L));
        }

        @Test
        @DisplayName("4.2 Should Throw when trying to delete Amenity")
        void shouldThrowNotFoundExceptionWhenDeleteAmenity() {
            when(service.findById(anyLong())).thenReturn(Optional.empty());
            assertThrows(NotFoundException.class, () -> service.delete(1L));
        }

        @Test
        @DisplayName("4.3 Should delete Amenity by Id Location")
        void shouldDeleteAmenityByIdLocation() {
            doNothing().when(repository).deleteByIdLocation(anyLong());
            assertDoesNotThrow(() -> service.deleteByIdLocation(1L));
        }

    }

}