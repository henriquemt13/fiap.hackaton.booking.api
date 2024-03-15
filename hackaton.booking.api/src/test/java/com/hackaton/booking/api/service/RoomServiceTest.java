package com.hackaton.booking.api.service;

import com.hackaton.booking.api.fixture.RoomFixture;
import com.hackaton.booking.api.repository.RoomRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import org.springframework.data.jpa.domain.Specification;

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
            when(repository.findAll(any(Specification.class))).thenReturn(expectedResponse);

            var rooms = service.findAll(RoomFixture.buildRoom());

            verify(repository, times(1)).findAll(any(Specification.class));
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
}