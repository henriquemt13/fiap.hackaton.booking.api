package com.hackaton.booking.api.usecase;

import com.hackaton.booking.api.repository.RoomRepository;
import com.hackaton.booking.api.service.BathroomService;
import com.hackaton.booking.api.service.BuildingService;
import com.hackaton.booking.api.service.FurnitureService;
import com.hackaton.booking.api.service.RoomService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

}