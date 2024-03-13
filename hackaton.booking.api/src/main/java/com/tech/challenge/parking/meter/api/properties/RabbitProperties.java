package com.tech.challenge.parking.meter.api.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.rabbitmq")
@Data
@Component
public class RabbitProperties {

    private String host;
    private Integer port;
    private String username;
    private String password;
    private Long recoveryInterval;
}