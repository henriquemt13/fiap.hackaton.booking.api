package com.tech.challenge.parking.meter.api.domain.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EstacionamentoInfosResponseDTO {

    private Integer vagasAlocadas;
    private Integer vagasDisponiveis;
    private Integer numeroMotosAlocadas;
    private Integer numeroCarroAlocados;
}
