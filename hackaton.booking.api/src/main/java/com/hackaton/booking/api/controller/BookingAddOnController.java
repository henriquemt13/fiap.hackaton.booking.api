package com.hackaton.booking.api.controller;

import com.hackaton.booking.api.domain.dto.request.BookingAddOnRequestDTO;
import com.hackaton.booking.api.domain.dto.response.AddOnResponseDTO;
import com.hackaton.booking.api.domain.dto.response.BookingAddOnResponseDTO;
import com.hackaton.booking.api.domain.mapper.AddOnMapper;
import com.hackaton.booking.api.domain.mapper.BookingAddOnMapper;
import com.hackaton.booking.api.exceptions.NotFoundException;
import com.hackaton.booking.api.service.AddOnService;
import com.hackaton.booking.api.service.BookingAddOnService;
import com.hackaton.booking.api.usecase.BookingAddOnUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.String.format;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/booking-add-on")
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

        return ResponseEntity.status(HttpStatus.CREATED).body(addOnMapper.of(bookingAddOnUseCase.findAllAddOns()));
    }

    @PostMapping
    @ApiResponse(description = "Booking AddOn Response", responseCode = "201")
    @Operation(summary = "Create Booking", description = """
          # Registra novo Adicional há Reserva
          ---
          
          """)
    public ResponseEntity<BookingAddOnResponseDTO> addToBooking(
            @RequestBody @Valid BookingAddOnRequestDTO requestDTO) {

        var response = mapper.of(bookingAddOnUseCase.saveAddOn(mapper.ofRequest(requestDTO)));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @ApiResponse(description = "Booking AddOn Response", responseCode = "200")
    @Operation(summary = "Find Booking By Id", description = """
          # Busca Adicionais da Reserva por Id
          ---
          
          """)
    public ResponseEntity<BookingAddOnResponseDTO> findById(@PathVariable("id") @Valid Long id) {
        var optResponse = bookingAddOnUseCase.findById(id);
        if (optResponse.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(mapper.of(optResponse.get()));
        }
        throw new NotFoundException(format("Booking ID %d not found", id));
    }

    @PutMapping("/{id}")
    @ApiResponse(description = "Booking AddOn Response", responseCode = "200")
    @Operation(summary = "Update Booking By Id", description = """
          # Atualiza Adicionais da Reserva por Id
          ---
          
          """)
    public ResponseEntity<BookingAddOnResponseDTO> update(@PathVariable("id") @Valid Long id,
                                                    @RequestBody @Valid BookingAddOnRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(mapper.of(bookingAddOnUseCase.update(id,
                mapper.ofRequest(requestDTO))));
    }

    @DeleteMapping("/{id}")
    @ApiResponse(description = "Booking AddOn Response", responseCode = "200")
    @Operation(summary = "Delete Booking By Id", description = """
          # Apaga Adicionais da Reserva por Id
          ---
          
          """)
    public ResponseEntity<BookingAddOnResponseDTO> removeFromBooking(@PathVariable("id") @Valid Long id) {
        bookingAddOnUseCase.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
