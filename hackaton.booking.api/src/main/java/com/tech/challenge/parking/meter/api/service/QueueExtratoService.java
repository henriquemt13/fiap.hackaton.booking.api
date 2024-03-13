package com.tech.challenge.parking.meter.api.service;

import com.tech.challenge.parking.meter.api.connector.RabbitConnector;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class QueueExtratoService {

    private final RabbitConnector connector;
    private final VeiculoService veiculoService;

    public void saveNewExtrato(String placa) {
        if (veiculoService.veiculoExists(placa)) {
            try {
                log.info("Sending a Message to create a Entrada Process for Placa {}", placa);
                connector.saveNewExtrato(placa);
            } catch(Exception e) {
                log.info("Error while Receiving Confirmation Message from Rabbit, ", e);
            }
    }
}
}
