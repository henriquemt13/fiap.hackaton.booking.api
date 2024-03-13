package com.hackaton.booking.management.api.controller;

import com.hackaton.booking.management.api.domain.dto.request.RoomRequestDTO;
import com.hackaton.booking.management.api.domain.dto.response.RoomResponseDTO;
import com.hackaton.booking.management.api.domain.mapper.FurnitureMapper;
import com.hackaton.booking.management.api.domain.mapper.RoomMapper;
import com.hackaton.booking.management.api.usecase.RoomUseCase;
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
@RequestMapping("/api/v1/room")
public class RoomController {

    private final RoomUseCase roomUseCase;
    private final RoomMapper mapper;
    private final FurnitureMapper furnitureMapper;

    @PostMapping
    @ApiResponse(description = "Room Response", responseCode = "201")
    @Operation(summary = "Create Room", description = """
            # Registra novo Quarto(Hospedagem)
            ---
                      
            """)
    public ResponseEntity<RoomResponseDTO> createRoom(
            @RequestBody @Valid RoomRequestDTO requestDTO) {
        var response = mapper.of(roomUseCase.save(mapper.of(requestDTO),
                furnitureMapper.ofRequest(requestDTO.getFurniture())));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @ApiResponse(description = "Room Response", responseCode = "200")
    @Operation(summary = "Get All Rooms", description = """
            # Busca todos os Quartos
            ---
                      
            """)
    public ResponseEntity<List<RoomResponseDTO>> getAll() {

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.of(roomUseCase.findAll()));
    }

    @PutMapping("/{id}")
    @ApiResponse(description = "Room Response", responseCode = "200")
    @Operation(summary = "Update Room Name by ID", description = """
            # Atualiza Quarto pelo ID
            ---
                      
            """)
    public ResponseEntity<RoomResponseDTO> updateRoom(
            @PathVariable("id") Long id,
            @RequestBody @Valid RoomRequestDTO requestDTO) {

        return ResponseEntity.status(HttpStatus.OK).body(mapper.of(roomUseCase.update(mapper.of(requestDTO),
                furnitureMapper.ofRequest(requestDTO.getFurniture()), id)));
    }

    @DeleteMapping("/{id}")
    @ApiResponse(description = "Void", responseCode = "204")
    @Operation(summary = "Delete Room By ID", description = """
            # Apaga Quarto por Id
            ---
                      
            """)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        roomUseCase.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/furniture/{id}")
    @ApiResponse(description = "Void", responseCode = "204")
    @Operation(summary = "Delete Furniture By ID", description = """
            # Apaga Mobília por Id
            ---
                      
            """)
    public ResponseEntity<Void> deleteFurnitureById(@PathVariable("id") Long id) {
        roomUseCase.deleteFurnitureById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
