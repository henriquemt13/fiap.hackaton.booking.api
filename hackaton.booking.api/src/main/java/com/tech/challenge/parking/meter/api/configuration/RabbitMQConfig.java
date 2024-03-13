package com.tech.challenge.parking.meter.api.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tech.challenge.parking.meter.api.properties.RabbitProperties;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
@EnableRabbit
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "ParkingMeter.ProcessFile.Queue";
    public static final String EXCHANGE_NAME = "ParkingMeter.ProcessFile.Exchange";
    public static final String ROUTING_KEY = "ParkingMeter.ProcessFile.RQ";
    public static final String DEAD_LETTER = "ParkingMeter.ProcessFile.DLQ";
    public static final String DEAD_LETTER_QUEUE = "ParkingMeter.ProcessFile.RK";
    public static final String EXCHANGE_DEAD_LETTER = "ParkingMeter.ProcessFile.DLQ.Exchange";
    private final RabbitProperties rabbitProperties;

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(EXCHANGE_DEAD_LETTER);
    }

    @Bean
    public Queue queue() {
        return QueueBuilder.durable(QUEUE_NAME).build();
    }

    @Bean
    public Queue dlqQueue() {
        return QueueBuilder.durable(DEAD_LETTER_QUEUE).build();
    }

    @Bean
    public Binding biding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(ROUTING_KEY);
    }

    @Bean
    public Binding DLQBinding() {
        return BindingBuilder.bind(dlqQueue()).to(deadLetterExchange()).with(DEAD_LETTER);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter(ObjectMapper mapper) {
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(mapper);
    }

    @Bean
    public CachingConnectionFactory cachingConnectionFactory() {
        CachingConnectionFactory cFactory = new CachingConnectionFactory();
        cFactory.setHost(rabbitProperties.getHost());
        cFactory.setPort(rabbitProperties.getPort());
        cFactory.setUsername(rabbitProperties.getUsername());
        cFactory.setPassword(rabbitProperties.getPassword());
        cFactory.getRabbitConnectionFactory().setAutomaticRecoveryEnabled(true);
        return cFactory;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory listenerConfiguration(
            SimpleRabbitListenerContainerFactory factory) {
        factory.setRecoveryInterval(rabbitProperties.getRecoveryInterval());
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return factory;
    }
}
