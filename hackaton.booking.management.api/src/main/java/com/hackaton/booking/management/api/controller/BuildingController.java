package com.hackaton.booking.management.api.controller;

import com.hackaton.booking.management.api.domain.dto.request.BuildingRequestDTO;
import com.hackaton.booking.management.api.domain.dto.response.BuildingResponseDTO;
import com.hackaton.booking.management.api.domain.mapper.BuildingMapper;
import com.hackaton.booking.management.api.domain.mapper.RoomMapper;
import com.hackaton.booking.management.api.exceptions.NotFoundException;
import com.hackaton.booking.management.api.usecase.BuildingUseCase;
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
@RequestMapping("/api/v1/building")
public class BuildingController {

    private final BuildingUseCase buildingUseCase;
    private final BuildingMapper mapper;
    private final RoomMapper roomMapper;

    @PostMapping
    @ApiResponse(description = "Building Response", responseCode = "201")
    @Operation(summary = "Create Building", description = """
          # Registra novo Prédio
          ---
          
          """)
    public ResponseEntity<BuildingResponseDTO> createBuilding(
            @RequestBody @Valid BuildingRequestDTO requestDTO) {

        var response = mapper.of(buildingUseCase.save(mapper.of(requestDTO)));
        response.setRooms(roomMapper.of(buildingUseCase.findRoomsByIdBuilding(response.getId())));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @ApiResponse(description = "Building Response", responseCode = "200")
    @Operation(summary = "Get All Buildings", description = """
          # Busca todos os Prédios
          ---
          
          """)
    public ResponseEntity<List<BuildingResponseDTO>> getAll() {

        return ResponseEntity.status(HttpStatus.OK).body(mapper.of(buildingUseCase.findAll()));
    }

    @GetMapping("/{id}")
    @ApiResponse(description = "Building Response", responseCode = "200")
    @Operation(summary = "Get Building by Id", description = """
          # Busca Prédio por Id
          ---
          
          """)
    public ResponseEntity<BuildingResponseDTO> findById(@PathVariable("id") @Valid Long id) {
        var optBuilding = buildingUseCase.findById(id);
        if (optBuilding.isPresent()) {
            var response = mapper.of(optBuilding.get());
            response.setRooms(roomMapper.of(buildingUseCase.findRoomsByIdBuilding(response.getId())));
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        throw new NotFoundException(format("Building ID %d not found", id));
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

        var response = mapper.of(buildingUseCase.update(mapper.of(requestDTO), id));
        response.setRooms(roomMapper.of(buildingUseCase.findRoomsByIdBuilding(response.getId())));
        return ResponseEntity.status(HttpStatus.OK).body(response);
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
