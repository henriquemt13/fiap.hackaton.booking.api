package com.tech.challenge.parking.meter.api.exceptions;

public class InvalidAlocacaoException extends BadRequestException {

    public InvalidAlocacaoException(String placa) {
        super(String.format("Invalid Alocacao, Placa [%s]", placa));
    }

    public InvalidAlocacaoException(Long id) {
        super(String.format("Invalid Alocacao, Id [%d]", id));
    }
}
