package com.tech.challenge.parking.meter.api.connector;

import com.tech.challenge.parking.meter.api.configuration.RabbitMQConfig;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RabbitConnector {

    private final AmqpTemplate amqpTemplate;

    public void saveNewExtrato(String placa) {
        amqpTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY,
               placa);
    }
}