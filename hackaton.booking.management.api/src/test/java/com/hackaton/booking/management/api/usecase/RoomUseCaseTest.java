package com.hackaton.booking.management.api.usecase;

import com.hackaton.booking.management.api.exceptions.NotFoundException;
import com.hackaton.booking.management.api.fixture.*;
import com.hackaton.booking.management.api.repository.RoomRepository;
import com.hackaton.booking.management.api.service.BathroomService;
import com.hackaton.booking.management.api.service.BuildingService;
import com.hackaton.booking.management.api.service.FurnitureService;
import com.hackaton.booking.management.api.service.RoomService;
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
class RoomUseCaseTest {

    @InjectMocks
    private RoomUseCase roomUseCase;

    @Mock
    private RoomService service;

    @Mock
    private RoomRepository repository;

    @Mock
    private FurnitureService furnitureService;

    @Mock
    private BathroomService bathroomService;

    @Mock
    private BuildingService buildingService;

    @Nested
    @DisplayName("1. Save Rooms")
    class SaveRooms {

        @Test
        @DisplayName("1.1 Should save new Room and its Furnitures")
        void shouldSaveRoom() {
            when(buildingService.findById(anyLong())).thenReturn(Optional.of(BuildingFixture.buildBuilding()));
            when(repository.save(any())).thenReturn(RoomFixture.buildRoom());
            when(bathroomService.findByType(any())).thenReturn(Optional.of(BathroomFixture.buildBathroom()));
            doNothing().when(furnitureService).saveAll(any());
            assertDoesNotThrow(() -> roomUseCase.save(RoomFixture.buildRoom(), FurnitureFixture.buildFurnitures()));
        }

        @Test
        @DisplayName("1.2 Should not find an existent Building ID")
        void shouldThrowNotFoundExceptionWhenTryingFindBuildingId() {
            when(buildingService.findById(anyLong())).thenReturn(Optional.empty());
            assertThrows(NotFoundException.class, () ->
                    roomUseCase.save(RoomFixture.buildRoom(), FurnitureFixture.buildFurnitures()));
        }

        @Test
        @DisplayName("1.2 Should not find an existent Bathroom By Type")
        void shouldThrowNotFoundExceptionWhenTryingFindBathroom() {
            when(buildingService.findById(anyLong())).thenReturn(Optional.of(BuildingFixture.buildBuilding()));
            when(bathroomService.findByType(any())).thenReturn(Optional.empty());
            assertThrows(NotFoundException.class, () ->
                    roomUseCase.save(RoomFixture.buildRoom(), FurnitureFixture.buildFurnitures()));
        }

    }

    @Nested
    @DisplayName("2. Update Rooms")
    class UpdateRooms {


        @Test
        @DisplayName("3.1 Should Update Room")
        void shouldUpdateRoom() {
            var updateResponse = RoomFixture.buildRoom();
            updateResponse.setTotalRooms(3L);

            when(buildingService.findById(anyLong())).thenReturn(Optional.of(BuildingFixture.buildBuilding()));
            doNothing().when(furnitureService).updateAll(FurnitureFixture.buildFurnitures(), 1L);
            when(repository.findById(anyLong())).thenReturn(Optional.of(RoomFixture.buildRoom()));
            when(repository.save(any())).thenReturn(updateResponse);

            var rooms = roomUseCase.update(updateResponse, FurnitureFixture.buildFurnitures());

            verify(repository, times(1)).save(any());
            verify(buildingService, times(1)).findById(anyLong());
            verify(furnitureService, times(1)).updateAll(any(), anyLong());
            assertEquals(updateResponse, rooms);
        }

        @Test
        @DisplayName("3.2 Should Throw When Trying to Update a Non-Existent Room")
        void shouldUThrowNotFoundExceptionWhenUpdateRoom() {
            var updateResponse = RoomFixture.buildRoom();
            updateResponse.setTotalRooms(3L);

            when(buildingService.findById(anyLong())).thenReturn(Optional.empty());

            assertThrows(NotFoundException.class, () -> roomUseCase.update(updateResponse,
                    FurnitureFixture.buildFurnitures()));
        }

    }

    @Nested
    @DisplayName("3. Delete Rooms")
    class DeleteRooms {

        @Test
        @DisplayName("4.1 Should delete Room")
        void shouldDeleteRoom() {
            when(repository.findById(anyLong())).thenReturn(Optional.of(RoomFixture.buildRoom()));
            doNothing().when(furnitureService).deleteByIdRoom(anyLong());
            assertDoesNotThrow(() -> roomUseCase.delete(1L));
        }

        @Test
        @DisplayName("4.2 Should Delete Furniture")
        void shouldDeleteFurnitureById() {
            doNothing().when(furnitureService).delete(any());
            assertDoesNotThrow(() -> roomUseCase.deleteFurnitureById(1L));
        }

    }
}