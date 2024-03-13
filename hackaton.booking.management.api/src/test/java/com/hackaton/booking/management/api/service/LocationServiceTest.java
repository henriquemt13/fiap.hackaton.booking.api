package com.hackaton.booking.management.api.service;

import com.hackaton.booking.management.api.exceptions.NotFoundException;
import com.hackaton.booking.management.api.fixture.LocationFixture;
import com.hackaton.booking.management.api.repository.LocationRepository;
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
class LocationServiceTest {

    @InjectMocks
    private LocationService service;

    @Mock
    private LocationRepository repository;

    @Nested
    @DisplayName("1. Find Locations")
    class FindLocations {

        @Test
        @DisplayName("1.1 Should find All Locations")
        void shouldFindAll() {
            var expectedResponse = LocationFixture.buildLocations();
            when(repository.findAll()).thenReturn(expectedResponse);

            var locations = service.findAll();

            verify(repository, times(1)).findAll();
            assertEquals(expectedResponse, locations);
        }

        @Test
        @DisplayName("1.2 Should find Location by Id")
        void shouldFindById() {
            var expectedResponse = Optional.of(LocationFixture.buildLocation());
            when(repository.findById(anyLong())).thenReturn(expectedResponse);

            var location = service.findById(1L);

            verify(repository, times(1)).findById(anyLong());
            assertEquals(expectedResponse, location);
        }

        @Test
        @DisplayName("1.3 Should not find Location for given Id")
        void shouldNotFindById() {
            when(repository.findById(anyLong())).thenReturn(Optional.empty());

            var location = service.findById(1L);

            verify(repository, times(1)).findById(anyLong());
            assertEquals(Optional.empty(), location);
        }

        @Test
        @DisplayName("1.4 Should not find Location for given Name like")
        void shouldNotFindByNameContains() {
            when(repository.findByNameContains(anyString())).thenReturn(LocationFixture.buildLocations());

            var locations = service.findByNameContains("Tes");

            verify(repository, times(1)).findByNameContains(anyString());
            assertEquals(LocationFixture.buildLocations(), locations);
        }
    }

    @Nested
    @DisplayName("2. Save Locations")
    class SaveLocations {

        @Test
        @DisplayName("2.1 Should save All Locations")
        void shouldSaveLocation() {
            when(repository.save(any())).thenReturn(LocationFixture.buildLocation());

            assertDoesNotThrow(() -> service.save(LocationFixture.buildLocation()));
        }

    }

    @Nested
    @DisplayName("3. Update Locations")
    class UpdateLocations {

        @Test
        @DisplayName("3.1 Should Update Location")
        void shouldUpdateLocation() {
            var updateResponse = LocationFixture.buildLocation();
            updateResponse.setName("Test3");

            when(repository.findById(anyLong())).thenReturn(Optional.of(LocationFixture.buildLocation()));
            when(repository.save(any())).thenReturn(updateResponse);

            var location = service.update(1L, updateResponse);

            verify(repository, times(1)).save(any());
            verify(repository, times(1)).findById(anyLong());
            assertEquals(updateResponse, location);
        }

        @Test
        @DisplayName("3.2 Should Throw When Trying to Update a Non-Existent Location")
        void shouldUThrowNotFoundExceptionWhenUpdateLocation() {
            var updateResponse = LocationFixture.buildLocation();
            updateResponse.setName("Test3");

            when(repository.findById(anyLong())).thenReturn(Optional.empty());

            assertThrows(NotFoundException.class, () -> service.update(1L, updateResponse));
        }

    }

    @Nested
    @DisplayName("4. Delete Locations")
    class DeleteLocations {

        @Test
        @DisplayName("4.1 Should delete Location")
        void shouldDeleteLocation() {
            when(repository.findById(anyLong())).thenReturn(Optional.of(LocationFixture.buildLocation()));
            doNothing().when(repository).delete(LocationFixture.buildLocation());
            assertDoesNotThrow(() -> service.delete(1L));
        }

        @Test
        @DisplayName("4.2 Should Throw when trying to delete Location")
        void shouldThrowNotFoundExceptionWhenDeleteLocation() {
            when(service.findById(anyLong())).thenReturn(Optional.empty());
            assertThrows(NotFoundException.class, () -> service.delete(1L));
        }


    }

}