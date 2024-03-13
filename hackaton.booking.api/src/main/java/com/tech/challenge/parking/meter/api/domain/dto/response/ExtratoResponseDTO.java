package com.tech.challenge.parking.meter.api.domain.dto.response;


import com.tech.challenge.parking.meter.api.enums.TempoContratadoEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ExtratoResponseDTO {

    private LocalDateTime entrada;
    private LocalDateTime saida;
    private TempoContratadoEnum tempoContratado;
    private BigDecimal valorPago;
}
