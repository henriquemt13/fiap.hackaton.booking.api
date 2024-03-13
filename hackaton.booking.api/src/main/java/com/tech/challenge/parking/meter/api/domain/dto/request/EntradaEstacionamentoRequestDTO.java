package com.tech.challenge.parking.meter.api.domain.dto.request;

import com.tech.challenge.parking.meter.api.enums.TempoContratadoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EntradaEstacionamentoRequestDTO {

    @NotNull(message = "tempoContratado should not be null")
    private TempoContratadoEnum tempoContratado;
    @NotNull(message = "enderecoEstacionamento should not be null")
    @NotBlank(message = "enderecoEstacionamento should not be null")
    private String enderecoEstacionamento;
}
