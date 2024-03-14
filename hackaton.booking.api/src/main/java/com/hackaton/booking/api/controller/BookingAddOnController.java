package com.hackaton.booking.api.controller;

import static java.lang.String.format;

import com.hackaton.booking.api.domain.dto.request.BookingAddOnRequestDTO;
import com.hackaton.booking.api.domain.dto.response.AddOnResponseDTO;
import com.hackaton.booking.api.domain.dto.response.BookingAddOnResponseDTO;
import com.hackaton.booking.api.domain.mapper.AddOnMapper;
import com.hackaton.booking.api.domain.mapper.BookingAddOnMapper;
import com.hackaton.booking.api.exceptions.NotFoundException;
import com.hackaton.booking.api.usecase.BookingAddOnUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/booking-add-on")
@Slf4j
public class BookingAddOnController {

    private final BookingAddOnUseCase bookingAddOnUseCase;
    private final BookingAddOnMapper mapper;
    private final AddOnMapper addOnMapper;

    @GetMapping
    @ApiResponse(description = "AddOn Response", responseCode = "200")
    @Operation(summary = "Get All AddOns", description = """
          # Busca todos os Serviços Adicionais
          ---
                    
          """)
    public ResponseEntity<List<AddOnResponseDTO>> getAll() {
        log.info("Find all AddOns");
        return ResponseEntity.status(HttpStatus.CREATED)
              .body(addOnMapper.of(bookingAddOnUseCase.findAllAddOns()));
    }

    @PostMapping
    @ApiResponse(description = "Booking AddOn Response", responseCode = "201")
    @Operation(summary = "Create Booking AddOn", description = """
          # Registra novo Adicional há Reserva
          ---
                    
          """)
    public ResponseEntity<BookingAddOnResponseDTO> addToBooking(
          @RequestBody @Valid BookingAddOnRequestDTO requestDTO) {
        log.info(format("Add Booking Add On ID %d to Booking ID %d", requestDTO.getIdAddOn(),
              requestDTO.getIdBooking()));
        var response = mapper.of(bookingAddOnUseCase.saveAddOn(mapper.ofRequest(requestDTO)));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @ApiResponse(description = "Booking AddOn Response", responseCode = "200")
    @Operation(summary = "Find Booking AddOn By Id", description = """
          # Busca Adicionais da Reserva por Id
          ---
                    
          """)
    public ResponseEntity<BookingAddOnResponseDTO> findById(@PathVariable("id") @Valid Long id) {
        log.info(format("Searching for Booking Add On ID %d", id));
        var optResponse = bookingAddOnUseCase.findById(id);
        if (optResponse.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(mapper.of(optResponse.get()));
        }
        throw new NotFoundException(format("Booking ID %d not found", id));
    }

    @PutMapping("/{id}")
    @ApiResponse(description = "Booking AddOn Response", responseCode = "200")
    @Operation(summary = "Update Booking AddOn By Id", description = """
          # Atualiza Adicionais da Reserva por Id
          ---
                    
          """)
    public ResponseEntity<BookingAddOnResponseDTO> update(@PathVariable("id") @Valid Long id,
          @RequestBody @Valid BookingAddOnRequestDTO requestDTO) {
        log.info(format("Updating Booking ID %d", id));
        return ResponseEntity.status(HttpStatus.OK).body(mapper.of(bookingAddOnUseCase.update(id,
              mapper.ofRequest(requestDTO))));
    }

    @DeleteMapping("/{id}")
    @ApiResponse(description = "Booking AddOn Response", responseCode = "200")
    @Operation(summary = "Delete Booking By Id", description = """
          # Apaga Adicionais da Reserva por Id
          ---
                    
          """)
    public ResponseEntity<BookingAddOnResponseDTO> removeFromBooking(
          @PathVariable("id") @Valid Long id) {
        log.info(format("Removing Booking AddOn ID %d", id));
        bookingAddOnUseCase.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
