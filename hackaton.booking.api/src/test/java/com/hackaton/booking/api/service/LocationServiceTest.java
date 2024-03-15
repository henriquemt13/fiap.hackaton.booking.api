package com.hackaton.booking.api.service;

import com.hackaton.booking.api.domain.dto.request.LocationRequestDTO;
import com.hackaton.booking.api.fixture.LocationFixture;
import com.hackaton.booking.api.repository.LocationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import org.springframework.data.jpa.domain.Specification;

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
            when(repository.findAll(any(Specification.class))).thenReturn(expectedResponse);

            var locations = service.findAll(LocationFixture.buildLocation());

            verify(repository, times(1)).findAll(any(Specification.class));
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

}