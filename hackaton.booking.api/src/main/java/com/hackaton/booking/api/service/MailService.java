package com.hackaton.booking.api.service;

import com.hackaton.booking.api.builder.MailBuilder;
import com.hackaton.booking.api.domain.dto.response.LocationResponseDTO;
import com.hackaton.booking.api.domain.mapper.ClientMapper;
import com.hackaton.booking.api.exceptions.NotFoundException;
import com.hackaton.booking.api.properties.MailProperties;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@AllArgsConstructor
public class MailService {

    private final MailProperties properties;
    private final ClientService clientService;
    private final ClientMapper clientMapper;

    public void sendMail(LocationResponseDTO responseDTO, Long idClient) throws MessagingException {
        var mailDTO = properties.getProperties();
        var client = clientService.findById(idClient);
        if (client.isEmpty()) {
            throw new NotFoundException(format("Client ID %d not found", idClient));
        }
        mailDTO.setParams(responseDTO);
        mailDTO.setClientInfos(clientMapper.of(client.get()));
        JavaMailSender mailSender = MailBuilder.getJavaMailSender(mailDTO);
        mailSender.send(MailBuilder.buildMimeMessage(mailSender, mailDTO));
    }
}
