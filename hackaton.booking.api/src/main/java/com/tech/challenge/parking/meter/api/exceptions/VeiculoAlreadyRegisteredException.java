package com.tech.challenge.parking.meter.api.exceptions;

public class VeiculoAlreadyRegisteredException extends BadRequestException {
    public VeiculoAlreadyRegisteredException(String placa) {
        super(String.format("Veiculo with Placa [%s] already registered", placa));
    }
}
