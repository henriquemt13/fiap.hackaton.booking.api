package com.hackaton.booking.api.properties;

import com.hackaton.booking.api.domain.dto.MailDTO;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "mail")
public class MailProperties {

    private MailDTO properties;
}
