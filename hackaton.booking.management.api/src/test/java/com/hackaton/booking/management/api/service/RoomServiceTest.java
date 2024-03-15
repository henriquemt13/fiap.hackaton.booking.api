package com.hackaton.booking.management.api.service;

import com.hackaton.booking.management.api.exceptions.NotFoundException;
import com.hackaton.booking.management.api.fixture.RoomFixture;
import com.hackaton.booking.management.api.repository.RoomRepository;
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
class RoomServiceTest {

    @InjectMocks
    private RoomService service;

    @Mock
    private RoomRepository repository;

    @Nested
    @DisplayName("1. Find Rooms")
    class FindRooms {

        @Test
        @DisplayName("1.1 Should find All Rooms")
        void shouldFindAll() {
            var expectedResponse = RoomFixture.buildRooms();
            when(repository.findAll()).thenReturn(expectedResponse);

            var rooms = service.findAll();

            verify(repository, times(1)).findAll();
            assertEquals(expectedResponse, rooms);
        }

        @Test
        @DisplayName("1.2 Should find Room by Id")
        void shouldFindById() {
            var expectedResponse = Optional.of(RoomFixture.buildRoom());
            when(repository.findById(anyLong())).thenReturn(expectedResponse);

            var Room = service.findById(1L);

            verify(repository, times(1)).findById(anyLong());
            assertEquals(expectedResponse, Room);
        }

        @Test
        @DisplayName("1.3 Should not find Room for given Id")
        void shouldNotFindById() {
            when(repository.findById(anyLong())).thenReturn(Optional.empty());

            var room = service.findById(1L);

            verify(repository, times(1)).findById(anyLong());
            assertEquals(Optional.empty(), room);
        }
    }

    @Nested
    @DisplayName("2. Save Rooms")
    class SaveRooms {

        @Test
        @DisplayName("2.1 Should save All Rooms")
        void shouldSaveRoom() {
            when(repository.save(any())).thenReturn(RoomFixture.buildRoom());

            assertDoesNotThrow(() -> service.save(RoomFixture.buildRoom()));
        }

    }

    @Nested
    @DisplayName("3. Update Rooms")
    class UpdateRooms {

        @Test
        @DisplayName("3.1 Should Update Room")
        void shouldUpdateRoom() {
            var updateResponse = RoomFixture.buildRoom();
            updateResponse.setTotalRooms(3L);

            when(repository.findById(anyLong())).thenReturn(Optional.of(RoomFixture.buildRoom()));
            when(repository.save(any())).thenReturn(updateResponse);

            var rooms = service.update(updateResponse, 1L);

            verify(repository, times(1)).save(any());
            verify(repository, times(1)).findById(anyLong());
            assertEquals(updateResponse, rooms);
        }

        @Test
        @DisplayName("3.2 Should Throw When Trying to Update a Non-Existent Room")
        void shouldUThrowNotFoundExceptionWhenUpdateRoom() {
            var updateResponse = RoomFixture.buildRoom();
            updateResponse.setTotalRooms(3L);

            when(repository.findById(anyLong())).thenReturn(Optional.empty());

            assertThrows(NotFoundException.class, () -> service.update(updateResponse, 1L));
        }

    }

    @Nested
    @DisplayName("4. Delete Rooms")
    class DeleteRooms {

        @Test
        @DisplayName("4.1 Should delete Room")
        void shouldDeleteRoom() {
            when(repository.findById(anyLong())).thenReturn(Optional.of(RoomFixture.buildRoom()));
            doNothing().when(repository).delete(RoomFixture.buildRoom());
            assertDoesNotThrow(() -> service.delete(1L));
        }

        @Test
        @DisplayName("4.2 Should Throw when trying to delete Room")
        void shouldThrowNotFoundExceptionWhenDeleteRoom() {
            when(service.findById(anyLong())).thenReturn(Optional.empty());
            assertThrows(NotFoundException.class, () -> service.delete(1L));
        }

    }
}