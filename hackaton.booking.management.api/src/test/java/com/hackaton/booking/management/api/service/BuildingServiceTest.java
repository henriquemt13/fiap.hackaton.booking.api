package com.hackaton.booking.management.api.service;

import com.hackaton.booking.management.api.exceptions.NotFoundException;
import com.hackaton.booking.management.api.fixture.BuildingFixture;
import com.hackaton.booking.management.api.repository.BuildingRepository;
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

    @Nested
    @DisplayName("2. Save Buildings")
    class SaveBuildings {

        @Test
        @DisplayName("2.1 Should save Building")
        void shouldSaveBuilding() {
            when(repository.save(any())).thenReturn(BuildingFixture.buildBuilding());

            assertDoesNotThrow(() -> service.save(BuildingFixture.buildBuilding()));
        }

    }

    @Nested
    @DisplayName("3. Update Buildings")
    class UpdateBuildings {

        @Test
        @DisplayName("3.1 Should Update Building")
        void shouldUpdateBuilding() {
            var updateResponse = BuildingFixture.buildBuilding();
            updateResponse.setName("Test3");

            when(repository.findById(anyLong())).thenReturn(Optional.of(BuildingFixture.buildBuilding()));
            when(repository.save(any())).thenReturn(updateResponse);

            var building = service.update(updateResponse, 1L);

            verify(repository, times(1)).save(any());
            verify(repository, times(1)).findById(anyLong());
            assertEquals(updateResponse, building);
        }

        @Test
        @DisplayName("3.2 Should Throw When Trying to Update a Non-Existent Building")
        void shouldUThrowNotFoundExceptionWhenUpdateBuilding() {
            var updateResponse = BuildingFixture.buildBuilding();
            updateResponse.setName("Test3");

            when(repository.findById(anyLong())).thenReturn(Optional.empty());

            assertThrows(NotFoundException.class, () -> service.update(updateResponse, 1L));
        }

    }

    @Nested
    @DisplayName("4. Delete Buildings")
    class DeleteBuildings {

        @Test
        @DisplayName("4.1 Should delete Building")
        void shouldDeleteBuilding() {
            when(repository.findById(anyLong())).thenReturn(Optional.of(BuildingFixture.buildBuilding()));
            doNothing().when(repository).delete(BuildingFixture.buildBuilding());
            assertDoesNotThrow(() -> service.delete(1L));
        }

        @Test
        @DisplayName("4.2 Should Throw when trying to delete Building")
        void shouldThrowNotFoundExceptionWhenDeleteBuilding() {
            when(service.findById(anyLong())).thenReturn(Optional.empty());
            assertThrows(NotFoundException.class, () -> service.delete(1L));
        }

    }

}