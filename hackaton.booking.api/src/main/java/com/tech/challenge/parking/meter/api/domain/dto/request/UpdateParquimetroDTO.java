package com.tech.challenge.parking.meter.api.domain.dto.request;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class UpdateParquimetroDTO {

    private Integer totalVagasCidade;
    private BigDecimal precoInicial;
}
