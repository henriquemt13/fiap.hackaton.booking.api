package com.hackaton.booking.api.service;

import com.hackaton.booking.api.fixture.FurnitureFixture;
import com.hackaton.booking.api.repository.FurnitureRepository;
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
class FurnitureServiceTest {

    @InjectMocks
    private FurnitureService service;

    @Mock
    private FurnitureRepository repository;

    @Nested
    @DisplayName("1. Find Furnitures")
    class FindFurnitures {

        @Test
        @DisplayName("1.1 Should find All Furnitures")
        void shouldFindAll() {
            var expectedResponse = FurnitureFixture.buildFurnitures();
            when(repository.findAll()).thenReturn(expectedResponse);

            var Furnitures = service.findAll();

            verify(repository, times(1)).findAll();
            assertEquals(expectedResponse, Furnitures);
        }

        @Test
        @DisplayName("1.2 Should find Furniture by Id")
        void shouldFindById() {
            var expectedResponse = Optional.of(FurnitureFixture.buildFurniture());
            when(repository.findById(anyLong())).thenReturn(expectedResponse);

            var Furniture = service.findById(1L);

            verify(repository, times(1)).findById(anyLong());
            assertEquals(expectedResponse, Furniture);
        }

        @Test
        @DisplayName("1.3 Should not find Furniture for given Id")
        void shouldNotFindById() {
            when(repository.findById(anyLong())).thenReturn(Optional.empty());

            var Furniture = service.findById(1L);

            verify(repository, times(1)).findById(anyLong());
            assertEquals(Optional.empty(), Furniture);
        }
    }

}