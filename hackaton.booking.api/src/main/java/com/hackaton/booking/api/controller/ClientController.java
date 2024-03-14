package com.hackaton.booking.api.controller;

import com.hackaton.booking.api.domain.dto.response.ClientResponseDTO;
import com.hackaton.booking.api.domain.mapper.ClientMapper;
import com.hackaton.booking.api.domain.dto.request.ClientRequestDTO;
import com.hackaton.booking.api.exceptions.NotFoundException;
import com.hackaton.booking.api.service.ClientService;
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
@RequestMapping("/api/v1/client")
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper mapper;

    @PostMapping
    @ApiResponse(description = "Client Response", responseCode = "201")
    @Operation(summary = "Create Client", description = """
          # Registra novo Cliente Usuário
          ---
          
          """)
    public ResponseEntity<ClientResponseDTO> createClient(
            @RequestBody @Valid ClientRequestDTO clientRequestDTO) {

        var response = mapper.of(clientService.save(mapper.ofRequest(clientRequestDTO)));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @ApiResponse(description = "Client Response", responseCode = "201")
    @Operation(summary = "Find All Clients", description = """
          # Busca todos os Clientes Usuários
          ---
          
          """)
    public ResponseEntity<List<ClientResponseDTO>> getAllClients() {
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.of(clientService.findAll()));
    }

    @GetMapping("/{id}")
    @ApiResponse(description = "Client Response", responseCode = "200")
    @Operation(summary = "Find Client By Id", description = """
          # Busca Cliente Usuário por Id
          ---
          
          """)
    public ResponseEntity<ClientResponseDTO> findById(@PathVariable("id") @Valid Long id) {
        var optResponse = clientService.findById(id);
        if (optResponse.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(mapper.of(optResponse.get()));
        }
        throw new NotFoundException(format("Client ID %d not found", id));
    }

    @PutMapping("/{id}")
    @ApiResponse(description = "Client Response", responseCode = "200")
    @Operation(summary = "Update Client By Id", description = """
          # Atualiza Cliente Usuário por Id
          ---
          
          """)
    public ResponseEntity<ClientResponseDTO> update(@PathVariable("id") @Valid Long id,
                                                    @RequestBody @Valid ClientRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(mapper.of(clientService.update(id,
                mapper.ofRequest(requestDTO))));
    }

    @DeleteMapping("/{id}")
    @ApiResponse(description = "Client Response", responseCode = "200")
    @Operation(summary = "Delete Client By Id", description = """
          # Apaga Cliente Usuário por Id
          ---
          
          """)
    public ResponseEntity<ClientResponseDTO> delete(@PathVariable("id") @Valid Long id) {
        clientService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
