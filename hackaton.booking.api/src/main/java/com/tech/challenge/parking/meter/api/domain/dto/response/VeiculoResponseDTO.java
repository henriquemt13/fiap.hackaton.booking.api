package com.tech.challenge.parking.meter.api.domain.dto.response;

import com.tech.challenge.parking.meter.api.enums.TipoVeiculoEnum;
import java.util.Date;
import lombok.Data;

@Data
public class VeiculoResponseDTO {

    private String placa;
    private TipoVeiculoEnum tipoVeiculo;
    private String modelo;
    private String cor;
    private String ano;
    private String nomeDono;
    private String documentoDono;
    private String contatoDono;
}
