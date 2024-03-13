package com.tech.challenge.parking.meter.api.exceptions;

public class VeiculoNotFoundException  extends NotFoundException {

    public VeiculoNotFoundException(String placa) {
        super(String.format("Veiculo with Placa [%s] not found", placa));
    }

}
