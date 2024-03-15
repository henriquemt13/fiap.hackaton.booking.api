package com.hackaton.booking.api.usecase;

import com.hackaton.booking.api.repository.BuildingRepository;
import com.hackaton.booking.api.service.LocationService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class BuildingUseCaseTest {

    @InjectMocks
    private BuildingUseCase buildingUseCase;

    @Mock
    private LocationService locationService;

    @Mock
    private BuildingRepository buildingRepository;

}