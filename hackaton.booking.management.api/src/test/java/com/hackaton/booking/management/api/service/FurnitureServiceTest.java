package com.hackaton.booking.management.api.service;

import com.hackaton.booking.management.api.exceptions.NotFoundException;
import com.hackaton.booking.management.api.fixture.FurnitureFixture;
import com.hackaton.booking.management.api.repository.FurnitureRepository;
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

    @Nested
    @DisplayName("2. Save Furnitures")
    class SaveFurnitures {

        @Test
        @DisplayName("2.1 Should save All Furnitures")
        void shouldSaveFurniture() {
            when(repository.saveAll(any())).thenReturn(List.of(FurnitureFixture.buildFurniture()));

            assertDoesNotThrow(() -> service.saveAll(FurnitureFixture.buildFurnitures()));
        }

    }

    @Nested
    @DisplayName("3. Update Furnitures")
    class UpdateFurnitures {

        @Test
        @DisplayName("3.1 Should Update Furniture")
        void shouldUpdateFurniture() {
            var updateResponse = FurnitureFixture.buildFurniture();
            updateResponse.setName("Test3");

            when(repository.findById(anyLong())).thenReturn(Optional.of(FurnitureFixture.buildFurniture()));
            when(repository.saveAll(any())).thenReturn(List.of(updateResponse));

            assertDoesNotThrow(() -> service.updateAll(List.of(updateResponse)));
        }

        @Test
        @DisplayName("3.2 Should Throw When Trying to Update a Non-Existent Furniture")
        void shouldUThrowNotFoundExceptionWhenUpdateFurniture() {
            var updateResponse = FurnitureFixture.buildFurniture();
            updateResponse.setName("Test3");

            when(repository.findById(anyLong())).thenReturn(Optional.empty());

            assertThrows(NotFoundException.class, () -> service.updateAll(List.of(updateResponse)));
        }

    }

    @Nested
    @DisplayName("4. Delete Furnitures")
    class DeleteFurnitures {

        @Test
        @DisplayName("4.1 Should delete Furniture")
        void shouldDeleteFurniture() {
            when(repository.findById(anyLong())).thenReturn(Optional.of(FurnitureFixture.buildFurniture()));
            doNothing().when(repository).delete(FurnitureFixture.buildFurniture());
            assertDoesNotThrow(() -> service.delete(1L));
        }

        @Test
        @DisplayName("4.2 Should Throw when trying to delete Furniture")
        void shouldThrowNotFoundExceptionWhenDeleteFurniture() {
            when(service.findById(anyLong())).thenReturn(Optional.empty());
            assertThrows(NotFoundException.class, () -> service.delete(1L));
        }

        @Test
        @DisplayName("4.3 Should delete Furniture by Id Room")
        void shouldDeleteFurnitureByIdLocation() {
            doNothing().when(repository).deleteByIdRoom(anyLong());
            assertDoesNotThrow(() -> service.deleteByIdRoom(1L));
        }

    }

}