package com.hackaton.booking.api.service;

import com.hackaton.booking.api.fixture.AmenityFixture;
import com.hackaton.booking.api.repository.AmenityRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

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

}