package com.hackaton.booking.management.api.controller;

import com.hackaton.booking.management.api.domain.dto.request.AddOnRequestDTO;
import com.hackaton.booking.management.api.domain.dto.response.AddOnResponseDTO;
import com.hackaton.booking.management.api.domain.mapper.AddOnMapper;
import com.hackaton.booking.management.api.service.AddOnService;
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
@RequestMapping("/api/v1/add-on")
public class AddOnController {

    private final AddOnService service;
    private final AddOnMapper mapper;

    @PostMapping
    @ApiResponse(description = "AddOn Response", responseCode = "201")
    @Operation(summary = "Create AddOn", description = """
          # Registra novo Serviço Adicional
          ---
          
          """)
    public ResponseEntity<AddOnResponseDTO> createAddOn(
            @RequestBody @Valid AddOnRequestDTO requestDTO) {

        var response = mapper.of(service.save(mapper.of(requestDTO)));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @ApiResponse(description = "AddOn Response", responseCode = "200")
    @Operation(summary = "Get All AddOns", description = """
          # Busca todos os Serviços Adicionais
          ---
          
          """)
    public ResponseEntity<List<AddOnResponseDTO>> getAll() {

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.of(service.findAll()));
    }

    @PutMapping("/{id}")
    @ApiResponse(description = "AddOn Response", responseCode = "200")
    @Operation(summary = "Update AddOn Name by ID", description = """
          # Atualiza o Serviço Adicional pelo  ID
          ---
          
          """)
    public ResponseEntity<AddOnResponseDTO> updateAddOn(
            @PathVariable("id") Long id,
            @RequestBody @Valid AddOnRequestDTO requestDTO) {

        return ResponseEntity.status(HttpStatus.OK).body(mapper.of(service.update(id, mapper.of(requestDTO))));
    }

    @DeleteMapping("/{id}")
    @ApiResponse(description = "Void", responseCode = "204")
    @Operation(summary = "Delete AddOn By ID", description = """
          # Apaga Serviço Adicional por Id
          ---
          
          """)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
