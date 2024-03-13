package com.hackaton.booking.management.api.service;

import com.hackaton.booking.management.api.exceptions.NotFoundException;
import com.hackaton.booking.management.api.fixture.AddOnFixture;
import com.hackaton.booking.management.api.repository.AddOnRepository;
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
class AddOnServiceTest {

    @InjectMocks
    private AddOnService service;

    @Mock
    private AddOnRepository repository;

    @Nested
    @DisplayName("1. Find AddOns")
    class FindAddOns {

        @Test
        @DisplayName("1.1 Should find All AddOns")
        void shouldFindAll() {
            var expectedResponse = AddOnFixture.buildAddOns();
            when(repository.findAll()).thenReturn(expectedResponse);

            var addOns = service.findAll();

            verify(repository, times(1)).findAll();
            assertEquals(expectedResponse, addOns);
        }

        @Test
        @DisplayName("1.2 Should find AddOn by Id")
        void shouldFindById() {
            var expectedResponse = Optional.of(AddOnFixture.buildAddOn());
            when(repository.findById(anyLong())).thenReturn(expectedResponse);

            var addOn = service.findById(1L);

            verify(repository, times(1)).findById(anyLong());
            assertEquals(expectedResponse, addOn);
        }

        @Test
        @DisplayName("1.3 Should not find AddOn for given Id")
        void shouldNotFindById() {
            when(repository.findById(anyLong())).thenReturn(Optional.empty());

            var addOn = service.findById(1L);

            verify(repository, times(1)).findById(anyLong());
            assertEquals(Optional.empty(), addOn);
        }
    }

    @Nested
    @DisplayName("2. Save AddOns")
    class SaveAddOns {

        @Test
        @DisplayName("2.1 Should save new AddOn")
        void shouldSaveAddOn() {
            var response = AddOnFixture.buildAddOn();
            when(repository.save(any())).thenReturn(response);

            var addOn = service.save(AddOnFixture.buildAddOn());

            verify(repository, times(1)).save(any());
            assertEquals(response, addOn);
        }

    }

    @Nested
    @DisplayName("3. Update AddOns")
    class UpdateAddOns {

        @Test
        @DisplayName("3.1 Should Update AddOn")
        void shouldUpdateAddOn() {
            var updateResponse = AddOnFixture.buildAddOn();
            updateResponse.setDescription("Test3");

            when(repository.findById(anyLong())).thenReturn(Optional.of(AddOnFixture.buildAddOn()));
            when(repository.save(any())).thenReturn(updateResponse);

            var addOn = service.update(1L, AddOnFixture.buildAddOn());

            verify(repository, times(1)).save(any());
            verify(repository, times(1)).findById(anyLong());
            assertEquals(updateResponse, addOn);
        }

        @Test
        @DisplayName("3.2 Should Throw When Trying to Update a Non-Existent AddOn")
        void shouldUThrowNotFoundExceptionWhenUpdateAddOn() {
            var updateResponse = AddOnFixture.buildAddOn();
            updateResponse.setDescription("Test3");

            when(repository.findById(anyLong())).thenReturn(Optional.empty());

            assertThrows(NotFoundException.class, () -> service.update(1L, updateResponse));

        }

    }

    @Nested
    @DisplayName("4. Delete AddOns")
    class DeleteAddOns {

        @Test
        @DisplayName("4.1 Should delete AddOn")
        void shouldDeleteAddOn() {
            when(repository.findById(anyLong())).thenReturn(Optional.of(AddOnFixture.buildAddOn()));
            doNothing().when(repository).delete(AddOnFixture.buildAddOn());
            assertDoesNotThrow(() -> service.delete(1L));
        }

        @Test
        @DisplayName("4.2 Should Throw when trying to delete AddOn")
        void shouldThrowNotFoundExceptionWhenDeleteAddOn() {
            when(service.findById(anyLong())).thenReturn(Optional.empty());
            assertThrows(NotFoundException.class, () -> service.delete(1L));
        }


    }

}