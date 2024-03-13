package com.hackaton.booking.management.api.controller;

import com.hackaton.booking.management.api.domain.dto.request.LocationRequestDTO;
import com.hackaton.booking.management.api.domain.dto.response.LocationResponseDTO;
import com.hackaton.booking.management.api.domain.mapper.AmenityMapper;
import com.hackaton.booking.management.api.domain.mapper.LocationMapper;
import com.hackaton.booking.management.api.usecase.LocationUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/location")
public class LocationController {

    private final LocationUseCase locationUseCase;
    private final LocationMapper mapper;
    private final AmenityMapper amenityMapper;

    @PostMapping
    @ApiResponse(description = "Location Response", responseCode = "201")
    @Operation(summary = "Create Location", description = """
          # Registra nova Localidade
          ---
          
          """)
    public ResponseEntity<LocationResponseDTO> createLocation(
          @RequestBody @Valid LocationRequestDTO locationRequestDTO) {

        var response = mapper.of(locationUseCase.save(mapper.of(locationRequestDTO),
                amenityMapper.ofRequest(locationRequestDTO.getAmenities())));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @ApiResponse(description = "Location Response", responseCode = "200")
    @Operation(summary = "Get All Locations", description = """
          # Busca todas as Localidades
          ---
          
          """)
    public ResponseEntity<List<LocationResponseDTO>> getAll() {

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.of(locationUseCase.findAll()));
    }

    @PutMapping("/{id}")
    @ApiResponse(description = "Location Response", responseCode = "200")
    @Operation(summary = "Update Location Name by ID", description = """
          # Atualiza o nome da Localização pelo ID
          ---
          
          """)
    public ResponseEntity<LocationResponseDTO> updateLocation(
            @PathVariable("id") Long id,
            @RequestBody @Valid LocationRequestDTO requestDTO) {

        return ResponseEntity.status(HttpStatus.OK).body(mapper.of(locationUseCase.update(id, mapper.of(requestDTO))));
    }

    @DeleteMapping("/{id}")
    @ApiResponse(description = "Void", responseCode = "204")
    @Operation(summary = "Delete Location By ID", description = """
          # Apaga Localidade por Id
          ---
          
          """)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        locationUseCase.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/amenity/{id}")
    @ApiResponse(description = "Void", responseCode = "204")
    @Operation(summary = "Delete Amenity By ID", description = """
          # Apaga Amenidade por Id
          ---
          
          """)
    public ResponseEntity<Void> deleteAmenityById(@PathVariable("id") Long id) {
        locationUseCase.deleteAmenityById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    @ApiResponse(description = "Location Response", responseCode = "200")
    @Operation(summary = "Find By Location Name", description = """
          # Busca Localidade por Nome
          ---
          
          """)
    public ResponseEntity<List<LocationResponseDTO>> findByNameContains(@RequestParam @Valid String name) {

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.of(locationUseCase.findByNameContains(name)));
    }

}
