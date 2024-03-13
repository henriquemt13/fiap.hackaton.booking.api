package com.hackaton.booking.management.api.service;

import com.hackaton.booking.management.api.domain.enums.BathroomTypeEnum;
import com.hackaton.booking.management.api.exceptions.NotFoundException;
import com.hackaton.booking.management.api.fixture.BathroomFixture;
import com.hackaton.booking.management.api.repository.BathroomRepository;
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

            var bathroom = service.findByType(BathroomTypeEnum.STANDARD);

            verify(repository, times(1)).findByType(any());
            assertEquals(expectedResponse, bathroom);
        }

        @Test
        @DisplayName("1.5 Should not find Bathroom by Type")
        void shouldNotFindByType() {
            when(repository.findByType(any())).thenReturn(Optional.empty());

            var bathroom = service.findByType(BathroomTypeEnum.DELUXE);

            verify(repository, times(1)).findByType(any());
            assertEquals(Optional.empty(), bathroom);
        }
    }

    @Nested
    @DisplayName("2. Save Bathrooms")
    class SaveBathrooms {

        @Test
        @DisplayName("2.1 Should save new Bathroom")
        void shouldSaveBathroom() {
            when(repository.save(any())).thenReturn(BathroomFixture.buildBathroom());

            assertDoesNotThrow(() -> service.save(BathroomFixture.buildBathroom()));
        }

    }

    @Nested
    @DisplayName("3. Update Bathrooms")
    class UpdateBathrooms {

        @Test
        @DisplayName("3.1 Should Update Bathroom")
        void shouldUpdateBathroom() {
            var updateResponse = BathroomFixture.buildBathroom();
            updateResponse.setDescription("Test");

            when(repository.findById(anyLong())).thenReturn(Optional.of(BathroomFixture.buildBathroom()));
            when(repository.save(any())).thenReturn(updateResponse);

            var bathroom = service.update(updateResponse, 1L);

            verify(repository, times(1)).save(any());
            verify(repository, times(1)).findById(anyLong());
            assertEquals(updateResponse, bathroom);
        }

        @Test
        @DisplayName("3.2 Should Throw When Trying to Update a Non-Existent Bathroom")
        void shouldUThrowNotFoundExceptionWhenUpdateBathroom() {
            var updateResponse = BathroomFixture.buildBathroom();
            updateResponse.setDescription("Test3");

            when(repository.findById(anyLong())).thenReturn(Optional.empty());

            assertThrows(NotFoundException.class, () -> service.update( updateResponse, 1L));
        }

    }

    @Nested
    @DisplayName("4. Delete Bathrooms")
    class DeleteBathrooms {

        @Test
        @DisplayName("4.1 Should delete Bathroom")
        void shouldDeleteBathroom() {
            when(repository.findById(anyLong())).thenReturn(Optional.of(BathroomFixture.buildBathroom()));
            doNothing().when(repository).delete(BathroomFixture.buildBathroom());
            assertDoesNotThrow(() -> service.delete(1L));
        }

        @Test
        @DisplayName("4.2 Should Throw when trying to delete Bathroom")
        void shouldThrowNotFoundExceptionWhenDeleteBathroom() {
            when(service.findById(anyLong())).thenReturn(Optional.empty());
            assertThrows(NotFoundException.class, () -> service.delete(1L));
        }

    }
}