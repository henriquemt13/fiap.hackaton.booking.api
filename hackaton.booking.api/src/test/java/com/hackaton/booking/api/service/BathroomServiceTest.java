package com.hackaton.booking.api.service;

import com.hackaton.booking.api.domain.enums.BathroomTypeEnum;
import com.hackaton.booking.api.fixture.BathroomFixture;
import com.hackaton.booking.api.repository.BathroomRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BathroomServiceTest {

    @InjectMocks
    private BathroomService service;

    @Mock
    private BathroomRepository repository;

    @Nested
    @DisplayName("1. Find Bathrooms")
    class FindBathrooms {

        @Test
        @DisplayName("1.1 Should find All Bathrooms")
        void shouldFindAll() {
            var expectedResponse = BathroomFixture.buildBathrooms();
            when(repository.findAll()).thenReturn(expectedResponse);

            var bathroom = service.findAll();

            verify(repository, times(1)).findAll();
            assertEquals(expectedResponse, bathroom);
        }

        @Test
        @DisplayName("1.2 Should find Bathroom by Id")
        void shouldFindById() {
            var expectedResponse = Optional.of(BathroomFixture.buildBathroom());
            when(repository.findById(anyLong())).thenReturn(expectedResponse);

            var bathroom = service.findById(1L);

            verify(repository, times(1)).findById(anyLong());
            assertEquals(expectedResponse, bathroom);
        }

        @Test
        @DisplayName("1.3 Should not find Bathroom for given Id")
        void shouldNotFindById() {
            when(repository.findById(anyLong())).thenReturn(Optional.empty());

            var Bathroom = service.findById(1L);

            verify(repository, times(1)).findById(anyLong());
            assertEquals(Optional.empty(), Bathroom);
        }

        @Test
        @DisplayName("1.4 Should find Bathroom by Type")
        void shouldFindByType() {
            var expectedResponse = Optional.of(BathroomFixture.buildBathroom());
            when(repository.findByType(any())).thenReturn(expectedResponse);

            var bathroom = service.findByType(BathroomTypeEnum.STANDARD.name());

            verify(repository, times(1)).findByType(any());
            assertEquals(expectedResponse, bathroom);
        }

        @Test
        @DisplayName("1.5 Should not find Bathroom by Type")
        void shouldNotFindByType() {
            when(repository.findByType(any())).thenReturn(Optional.empty());

            var bathroom = service.findByType(BathroomTypeEnum.DELUXE.name());

            verify(repository, times(1)).findByType(any());
            assertEquals(Optional.empty(), bathroom);
        }
    }

}