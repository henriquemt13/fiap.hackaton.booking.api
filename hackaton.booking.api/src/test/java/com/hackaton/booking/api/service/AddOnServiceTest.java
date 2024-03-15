package com.hackaton.booking.api.service;

import com.hackaton.booking.api.fixture.AddOnFixture;
import com.hackaton.booking.api.repository.AddOnRepository;
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

}