package com.tech.challenge.parking.meter.api.listener;

import com.rabbitmq.client.Channel;
import com.tech.challenge.parking.meter.api.configuration.RabbitMQConfig;
import com.tech.challenge.parking.meter.api.service.ExtratoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@AllArgsConstructor
public class ProcessExtratoAlocacaoListener {

    private final ExtratoService extratoService;

    @RabbitListener(bindings = @QueueBinding(value = @Queue(RabbitMQConfig.QUEUE_NAME),
            exchange = @Exchange(RabbitMQConfig.EXCHANGE_NAME), key = RabbitMQConfig.ROUTING_KEY))
    public void processNewExtrato(final Channel chanel,
                            final String placa,
                            @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {

        log.info("Pending Extrato generation for Placa: {}", placa);
        try {
            extratoService.saveNewExtrato(placa);
            chanel.basicAck(tag, false);
        } catch (Exception e) {
            log.error("Invalid Message Delivered", e);
            chanel.basicReject(tag, false);
        }
    }
}