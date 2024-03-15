package com.hackaton.booking.api.controller;

import com.hackaton.booking.api.domain.dto.request.BookingFilterRequestDTO;
import com.hackaton.booking.api.domain.dto.request.BookingRequestDTO;
import com.hackaton.booking.api.domain.dto.response.BookingResponseDTO;
import com.hackaton.booking.api.domain.dto.response.BuildingResponseDTO;
import com.hackaton.booking.api.domain.dto.response.LocationResponseDTO;
import com.hackaton.booking.api.domain.dto.response.RoomResponseDTO;
import com.hackaton.booking.api.domain.enums.BathroomTypeEnum;
import com.hackaton.booking.api.domain.mapper.*;
import com.hackaton.booking.api.exceptions.NotFoundException;
import com.hackaton.booking.api.usecase.BookingAddOnUseCase;
import com.hackaton.booking.api.usecase.BookingUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/booking")
@Slf4j
public class BookingController {

    private final BookingUseCase bookingUseCase;
    private final BookingAddOnUseCase bookingAddOnUseCase;

    private final BookingMapper mapper;
    private final BookingAddOnMapper bookingAddOnMapper;

    @GetMapping("/new/search")
    @ApiResponse(description = "Location Response", responseCode = "200")
    @Operation(summary = "Find All Available Places", description = """
          # Busca todos os Lugares disponívels resultantes do filtros
          ---
                    
          """)
    public ResponseEntity<List<LocationResponseDTO>> getAllAvailablePlaces(
          @RequestBody @Valid BookingFilterRequestDTO filterDTO) {
        log.info(format("Searching for Available Rooms based on Filter: %s", filterDTO.toString()));
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
        log.info(format("Creating new Booking for Room ID %d, between %s and %s",
              bookingRequestDTO.getIdRoom(), bookingRequestDTO.getStartDate(),
              bookingRequestDTO.getEndDate()));
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
        log.info("Find All Bookings");
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.of(bookingUseCase.findAll()));
    }

    @GetMapping("/{id}")
    @ApiResponse(description = "Booking Response", responseCode = "200")
    @Operation(summary = "Find Booking By Id", description = """
          # Busca Reserva por Id
          ---
                    
          """)
    public ResponseEntity<BookingResponseDTO> findById(@PathVariable("id") @Valid Long id) {
        log.info(format("Searching for Booking ID %d", id));
        var optResponse = bookingUseCase.findById(id);
        if (optResponse.isPresent()) {
            var response = mapper.of(optResponse.get());
            response.setAddOns(
                  bookingAddOnMapper.of(bookingUseCase.findAddOnsByIdBooking(response.getId())));
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
        log.info(format("Updating Booking ID %d", id));
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
        log.info(format("Deleting  Booking ID %d", id));
        bookingUseCase.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @PostMapping("/{id}")
    @ApiResponse(description = "Booking Response", responseCode = "200")
    @Operation(summary = "Finish Booking By Id", description = """
          # Finaliza Reserva por ID
          ---
                    
          """)
    public ResponseEntity<BookingResponseDTO> finishBookingById(@PathVariable("id") @Valid Long id)
          throws MessagingException {
        log.info(format("Finish  Booking ID %d", id));
        bookingUseCase.finishBooking(id, validateAndSetResponseByBookingId(id));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    private LocationResponseDTO validateAndSetResponseByBookingId(Long id) {
        var booking = bookingUseCase.findBookingById(id);
        var room = bookingUseCase.findRoomById(booking.getIdRoom());
        var building = bookingUseCase.findBuildingById(room.getIdBuilding());
        var locationResponse = mapper.ofLocation(
              bookingUseCase.findLocationById(building.getIdLocation()));
        buildLocationResponseValues(locationResponse, mapper.ofRoom(room),
              mapper.ofBuilding(building), mapper.of(booking), room.getBathroomType());

        return locationResponse;
    }

    private void buildLocationResponseValues(LocationResponseDTO locationResponse,
          RoomResponseDTO roomResponse, BuildingResponseDTO buildingResponse,
          BookingResponseDTO bookingResponse, String bathroomType) {

        roomResponse.setFurniture(
              mapper.ofFurniture(bookingUseCase.findFurnitureByIdRoom(roomResponse.getId())));
        roomResponse.setBathroom(
              mapper.ofBathRoom(bookingUseCase.findBathroomByType(bathroomType)));
        buildingResponse.setRooms(List.of(roomResponse));
        locationResponse.setAmenities(
              mapper.ofAmenity(bookingUseCase.findAmenitiesByIdLocation(locationResponse.getId())));
        locationResponse.setBuildings(List.of(buildingResponse));
        locationResponse.setBooking(bookingResponse);
        locationResponse.setAddOns(mapper.ofAddOn(bookingAddOnUseCase.findAddOnsByIdBookingAddOn(
              locationResponse.getBooking().getId())));
    }


    private List<LocationResponseDTO> findPlaces(BookingFilterRequestDTO filterDTO) {
        var locations = mapper.ofLocation(
              bookingUseCase.findLocationsByFilter(mapper.ofLocationRequest(filterDTO)));
        locations.forEach(location -> location.setBuildings(
              findBuildingsByLocationId(location.getId(), filterDTO)));
        locations.removeIf(location -> location.getBuildings().isEmpty());
        locations.forEach(location -> location.setAmenities(
              mapper.ofAmenity(bookingUseCase.findAmenitiesByIdLocation(location.getId()))));
        return locations;
    }


    private List<BuildingResponseDTO> findBuildingsByLocationId(Long id,
          BookingFilterRequestDTO filterDTO) {
        var buildings = mapper.ofBuilding(bookingUseCase.findBuildingsByIdLocation(id));
        buildings.forEach(
              building -> building.setRooms(findRoomsByFilter(filterDTO, building.getId())));
        buildings.removeIf(building -> building.getRooms().isEmpty());
        return buildings;
    }

    private List<RoomResponseDTO> findRoomsByFilter(BookingFilterRequestDTO filterDTO,
          Long idBuilding) {
        var roomFilter = mapper.ofRoomRequest(filterDTO);
        roomFilter.setIdBuilding(idBuilding);
        var rooms = bookingUseCase.findRoomsByFilter(roomFilter, filterDTO.getStartDate(),
              filterDTO.getEndDate());
        List<RoomResponseDTO> response = new ArrayList<>();
        rooms.forEach(room -> {
            var responseRoom = mapper.ofRoom(room);
            responseRoom.setBathroom(
                  mapper.ofBathRoom(bookingUseCase.findBathroomByType(room.getBathroomType())));
            responseRoom.setFurniture(
                  mapper.ofFurniture(bookingUseCase.findFurnitureByIdRoom(room.getId())));
            response.add(responseRoom);
        });
        return response;
    }
}
