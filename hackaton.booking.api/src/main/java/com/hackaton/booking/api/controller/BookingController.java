package com.hackaton.booking.api.controller;

import com.hackaton.booking.api.domain.dto.request.BookingFilterRequestDTO;
import com.hackaton.booking.api.domain.dto.request.BookingRequestDTO;
import com.hackaton.booking.api.domain.dto.response.BookingResponseDTO;
import com.hackaton.booking.api.domain.dto.response.BuildingResponseDTO;
import com.hackaton.booking.api.domain.dto.response.LocationResponseDTO;
import com.hackaton.booking.api.domain.dto.response.RoomResponseDTO;
import com.hackaton.booking.api.domain.mapper.*;
import com.hackaton.booking.api.exceptions.NotFoundException;
import com.hackaton.booking.api.usecase.BookingUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {

    private final BookingUseCase bookingUseCase;

    private final BookingMapper mapper;
    private final LocationMapper locationMapper;
    private final RoomMapper roomMapper;
    private final BuildingMapper buildingMapper;
    private final AmenityMapper amenityMapper;
    private final BathroomMapper bathroomMapper;
    private final FurnitureMapper furnitureMapper;
    private final BookingAddOnMapper bookingAddOnMapper;

    @GetMapping("/new/search")
    @ApiResponse(description = "Location Response", responseCode = "200")
    @Operation(summary = "Find All Available Places", description = """
            # Busca todos os Lugares disponívels resultantes do filtros
            ---
                      
            """)
    public ResponseEntity<List<LocationResponseDTO>> getAllAvailablePlaces(@RequestBody @Valid BookingFilterRequestDTO filterDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(findPlaces(filterDTO));
    }

    @PostMapping
    @ApiResponse(description = "Booking Response", responseCode = "201")
    @Operation(summary = "Create Booking", description = """
            # Registra nova Reserva
            ---
                      
            """)
    public ResponseEntity<BookingResponseDTO> createBooking(
            @RequestBody @Valid BookingRequestDTO bookingRequestDTO) {

        var response = mapper.of(bookingUseCase.book(mapper.ofRequest(bookingRequestDTO)));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @ApiResponse(description = "Booking Response", responseCode = "201")
    @Operation(summary = "Find All Bookings", description = """
            # Busca todas as Reservas
            ---
                      
            """)
    public ResponseEntity<List<BookingResponseDTO>> getAllBookings() {
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.of(bookingUseCase.findAll()));
    }

    @GetMapping("/{id}")
    @ApiResponse(description = "Booking Response", responseCode = "200")
    @Operation(summary = "Find Booking By Id", description = """
            # Busca Reserva por Id
            ---
                      
            """)
    public ResponseEntity<BookingResponseDTO> findById(@PathVariable("id") @Valid Long id) {
        var optResponse = bookingUseCase.findById(id);
        if (optResponse.isPresent()) {
            var response = mapper.of(optResponse.get());
            response.setAddOns(bookingAddOnMapper.of(bookingUseCase.findAddOnsByIdBooking(response.getId())));
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        throw new NotFoundException(format("Booking ID %d not found", id));
    }

    @PutMapping("/{id}")
    @ApiResponse(description = "Booking Response", responseCode = "200")
    @Operation(summary = "Update Booking By Id", description = """
            # Atualiza Reserva por Id
            ---
                      
            """)
    public ResponseEntity<BookingResponseDTO> update(@PathVariable("id") @Valid Long id,
                                                     @RequestBody @Valid BookingRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(mapper.of(bookingUseCase.update(id,
                mapper.ofRequest(requestDTO))));
    }

    @DeleteMapping("/{id}")
    @ApiResponse(description = "Booking Response", responseCode = "200")
    @Operation(summary = "Delete Booking By Id", description = """
            # Apaga Reserva por Id
            ---
                      
            """)
    public ResponseEntity<BookingResponseDTO> delete(@PathVariable("id") @Valid Long id) {
        bookingUseCase.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    private List<LocationResponseDTO> findPlaces(BookingFilterRequestDTO filterDTO) {
        var locations = locationMapper.of(bookingUseCase.findLocationsByFilter(locationMapper.of(filterDTO)));
        locations.forEach(location -> location.setBuildings(findBuildingsByLocationId(location.getId(), filterDTO)));
        locations.removeIf(location -> location.getBuildings().isEmpty());
        locations.forEach(location -> location.setAmenities(amenityMapper.of(bookingUseCase.findAmenitiesByIdLocation(location.getId()))));
        return locations;
    }

    private List<BuildingResponseDTO> findBuildingsByLocationId(Long id, BookingFilterRequestDTO filterDTO) {
        var buildings = buildingMapper.of(bookingUseCase.findBuildingsByIdLocation(id));
        buildings.forEach(building -> building.setRooms(findRoomsByFilter(filterDTO, building.getId())));
        buildings.removeIf(building -> building.getRooms().isEmpty());
        return buildings;
    }

    private List<RoomResponseDTO> findRoomsByFilter(BookingFilterRequestDTO filterDTO, Long idBuilding) {
        var roomFilter = roomMapper.of(filterDTO);
        roomFilter.setIdBuilding(idBuilding);
        var rooms = bookingUseCase.findRoomsByFilter(roomFilter, filterDTO.getStartDate(), filterDTO.getEndDate());
        List<RoomResponseDTO> response = new ArrayList<>();
        rooms.forEach(room -> {
            var responseRoom = roomMapper.of(room);
            responseRoom.setBathroom(bathroomMapper.of(bookingUseCase.findBathroomByType(room.getBathroomType())));
            responseRoom.setFurniture(furnitureMapper.of(bookingUseCase.findFurnitureByIdRoom(room.getId())));
            response.add(responseRoom);
        });
        return response;
    }
}
