package com.tech.challenge.parking.meter.api.domain.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AlocacaoResponseDTO {

    private String placa;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private Integer horasAlocado;
    private String precoAPagar;

}
