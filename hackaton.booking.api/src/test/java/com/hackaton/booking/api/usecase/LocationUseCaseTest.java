package com.hackaton.booking.api.usecase;

import com.hackaton.booking.api.repository.LocationRepository;
import com.hackaton.booking.api.service.AmenityService;
import com.hackaton.booking.api.service.LocationService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class LocationUseCaseTest {

    @InjectMocks
    private LocationUseCase useCase;

    @Mock
    private LocationService service;

    @Mock
    private AmenityService amenityService;

    @Mock
    private LocationRepository repository;

}