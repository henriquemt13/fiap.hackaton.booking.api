package com.hackaton.booking.management.api.usecase;

import com.hackaton.booking.management.api.exceptions.NotFoundException;
import com.hackaton.booking.management.api.fixture.BuildingFixture;
import com.hackaton.booking.management.api.fixture.LocationFixture;
import com.hackaton.booking.management.api.repository.BuildingRepository;
import com.hackaton.booking.management.api.service.LocationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuildingUseCaseTest {

    @InjectMocks
    private BuildingUseCase buildingUseCase;

    @Mock
    private LocationService locationService;

    @Mock
    private BuildingRepository buildingRepository;

    @Nested
    @DisplayName("1. Save Buildings")
    class SaveBuildings {

        @Test
        @DisplayName("1.1 Should save Building")
        void shouldSaveBuilding() {
            when(locationService.findById(anyLong())).thenReturn(Optional.of(LocationFixture.buildLocation()));
            assertDoesNotThrow(() -> buildingUseCase.save(BuildingFixture.buildBuilding()));
        }

        @Test
        @DisplayName("1.2 Should Throw Exception when save Building")
        void shouldSaveBuildingThrowNotFoundException() {
            when(locationService.findById(anyLong())).thenReturn(Optional.empty());
            assertThrows(NotFoundException.class, () -> buildingUseCase.save(BuildingFixture.buildBuilding()));
        }

    }

    @Nested
    @DisplayName("2. Update Buildings")
    class UpdateBuildings {

        @Test
        @DisplayName("2.1 Should Update Building")
        void shouldUpdateBuilding() {
            when(locationService.findById(anyLong())).thenReturn(Optional.of(LocationFixture.buildLocation()));
            when(buildingRepository.findById(anyLong())).thenReturn(Optional.of(BuildingFixture.buildBuilding()));
            assertDoesNotThrow(() -> buildingUseCase.update(BuildingFixture.buildBuilding(), 1L));
        }

        @Test
        @DisplayName("2.2 Should Throw When Trying to Update with a Non-Existent Location ID")
        void shouldUThrowNotFoundExceptionWhenUpdateBuilding() {
            when(locationService.findById(anyLong())).thenReturn(Optional.empty());
            assertThrows(NotFoundException.class, () -> buildingUseCase.update(BuildingFixture.buildBuilding(), 1L));
        }

    }
}