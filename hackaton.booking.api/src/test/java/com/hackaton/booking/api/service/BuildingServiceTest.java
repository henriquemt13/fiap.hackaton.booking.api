package com.hackaton.booking.api.service;

import com.hackaton.booking.api.fixture.BuildingFixture;
import com.hackaton.booking.api.repository.BuildingRepository;
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
class BuildingServiceTest {

    @InjectMocks
    private BuildingService service;

    @Mock
    private BuildingRepository repository;

    @Nested
    @DisplayName("1. Find Buildings")
    class FindBuildings {

        @Test
        @DisplayName("1.1 Should find All Buildings")
        void shouldFindAll() {
            var expectedResponse = BuildingFixture.buildBuildings();
            when(repository.findAll()).thenReturn(expectedResponse);

            var buildings = service.findAll();

            verify(repository, times(1)).findAll();
            assertEquals(expectedResponse, buildings);
        }

        @Test
        @DisplayName("1.2 Should find Building by Id")
        void shouldFindById() {
            var expectedResponse = Optional.of(BuildingFixture.buildBuilding());
            when(repository.findById(anyLong())).thenReturn(expectedResponse);

            var building = service.findById(1L);

            verify(repository, times(1)).findById(anyLong());
            assertEquals(expectedResponse, building);
        }

        @Test
        @DisplayName("1.3 Should not find Building for given Id")
        void shouldNotFindById() {
            when(repository.findById(anyLong())).thenReturn(Optional.empty());

            var building = service.findById(1L);

            verify(repository, times(1)).findById(anyLong());
            assertEquals(Optional.empty(), building);
        }
    }

}