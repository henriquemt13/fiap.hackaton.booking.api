package com.hackaton.booking.management.api.controller;

import com.hackaton.booking.management.api.domain.dto.request.BuildingRequestDTO;
import com.hackaton.booking.management.api.domain.dto.response.BuildingResponseDTO;
import com.hackaton.booking.management.api.domain.mapper.BuildingMapper;
import com.hackaton.booking.management.api.usecase.BuildingUseCase;
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
@RequestMapping("/api/v1/building")
public class BuildingController {

    private final BuildingUseCase buildingUseCase;
    private final BuildingMapper mapper;

    @PostMapping
    @ApiResponse(description = "Building Response", responseCode = "201")
    @Operation(summary = "Create Building", description = """
          # Registra novo Prédio
          ---
          
          """)
    public ResponseEntity<BuildingResponseDTO> createBuilding(
            @RequestBody @Valid BuildingRequestDTO requestDTO) {

        var response = mapper.of(buildingUseCase.save(mapper.of(requestDTO)));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @ApiResponse(description = "Building Response", responseCode = "200")
    @Operation(summary = "Get All Buildings", description = """
          # Busca todos os Prédios
          ---
          
          """)
    public ResponseEntity<List<BuildingResponseDTO>> getAll() {

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.of(buildingUseCase.findAll()));
    }

    @PutMapping("/{id}")
    @ApiResponse(description = "Building Response", responseCode = "200")
    @Operation(summary = "Update Building Name by ID", description = """
          # Atualiza o Prédio pelo ID
          ---
          
          """)
    public ResponseEntity<BuildingResponseDTO> updateBuilding(
            @PathVariable("id") Long id,
            @RequestBody @Valid BuildingRequestDTO requestDTO) {

        return ResponseEntity.status(HttpStatus.OK).body(mapper.of(buildingUseCase.update(mapper.of(requestDTO), id)));
    }

    @DeleteMapping("/{id}")
    @ApiResponse(description = "Void", responseCode = "204")
    @Operation(summary = "Delete Building By ID", description = """
          # Apaga Prédio por Id
          ---
          
          """)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        buildingUseCase.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
