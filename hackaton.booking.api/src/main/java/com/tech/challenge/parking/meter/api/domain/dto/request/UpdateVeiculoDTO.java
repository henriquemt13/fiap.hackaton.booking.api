package com.tech.challenge.parking.meter.api.domain.dto.request;

import com.tech.challenge.parking.meter.api.enums.TipoVeiculoEnum;
import lombok.Data;

@Data
public class UpdateVeiculoDTO {

    private TipoVeiculoEnum tipoVeiculo;
    private String modelo;
    private String cor;
    private String ano;
    private String contatoDono;
}
