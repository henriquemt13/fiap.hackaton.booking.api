package com.tech.challenge.parking.meter.api.exceptions;

public class ExtratoNotFoundException extends NotFoundException {

    public ExtratoNotFoundException(String placa) {
        super(String.format("Extrato not found for sent Placa %s ", placa));
    }
}
