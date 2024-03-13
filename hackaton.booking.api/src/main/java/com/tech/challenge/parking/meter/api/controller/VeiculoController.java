package com.tech.challenge.parking.meter.api.controller;

import com.tech.challenge.parking.meter.api.domain.dto.request.UpdateVeiculoDTO;
import com.tech.challenge.parking.meter.api.domain.dto.request.VeiculoRequestDTO;
import com.tech.challenge.parking.meter.api.domain.dto.response.VeiculoResponseDTO;
import com.tech.challenge.parking.meter.api.domain.model.Veiculo;
import com.tech.challenge.parking.meter.api.service.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/veiculo")
public class VeiculoController {

    private final VeiculoService service;

    @PostMapping
    @ApiResponse(description = "Veiculo Response", responseCode = "201")
    @Operation(summary = "Create Veiculo", description = """
          # Registra novo Veículo
          ---
          notes:
          - No campo 'tipoVeiculo', informe CARRO ou MOTO;
          - No campo 'documento', informe o CPF ou CNPJ do dono do veículo;
          """)
    public ResponseEntity<VeiculoResponseDTO> createVeiculo(
          @RequestBody @Valid VeiculoRequestDTO veiculoRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
              .body(service.save(veiculoRequestDTO));
    }

    @PutMapping("/{placa}")
    @ApiResponse(description = "Void", responseCode = "200")
    @Operation(summary = "Update Veiculo by placa", description = """
          # Atualiza veículo pela placa
          ---
          notes:
          - Veiculo precisa existir no banco;
          """)
    public ResponseEntity<Void> updateVeiculo(@PathVariable("placa") String placa,
          @RequestBody @Valid UpdateVeiculoDTO veiculoDTO) {
        service.update(placa, veiculoDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    @ApiResponse(description = "Veiculo response", responseCode = "200")
    @Operation(summary = "Find All Veiculos", description = """
          # Busca todos os Veiculos
          ---
          notes:
          - Para busca sem filtros, apenas deixe "{}" nos parametros da requisição no Swagger,
            ou não envie nada via, se estiver via Postman ou Swagger;
          """)
    public ResponseEntity<List<VeiculoResponseDTO>> getAllVeiculos(
          VeiculoRequestDTO veiculoRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByFilter(veiculoRequestDTO));
    }
}
