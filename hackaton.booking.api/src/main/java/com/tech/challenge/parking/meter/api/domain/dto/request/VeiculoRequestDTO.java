package com.tech.challenge.parking.meter.api.domain.dto.request;

import com.tech.challenge.parking.meter.api.enums.TipoVeiculoEnum;
import com.tech.challenge.parking.meter.api.validator.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VeiculoRequestDTO {

    @NotNull(message = "placa should not be null")
    private String placa;
    @NotNull(message = "tipoVeiculo should not be null")
    private TipoVeiculoEnum tipoVeiculo;
    @NotNull(message = "Please enter modelo")
    @NotBlank(message = "Please enter modelo")
    private String modelo;
    @NotNull(message = "Please enter cor")
    @NotBlank(message = "Please enter cor")
    private String cor;
    @NotNull(message = "Please enter ano")
    private String ano;
    @NotNull(message = "Please enter nomeDono")
    @NotBlank(message = "Please enter nomeDono")
    private String nomeDono;
    @Document
    @NotNull(message = "Please enter documentoDono")
    @NotBlank(message = "Please enter documentoDono")
    private String documentoDono;
    @NotNull(message = "Please enter contatoDono")
    @NotBlank(message = "Please enter contatoDono")
    private String contatoDono;
}
