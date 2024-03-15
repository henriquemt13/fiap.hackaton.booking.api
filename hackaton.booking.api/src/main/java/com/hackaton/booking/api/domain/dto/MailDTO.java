package com.hackaton.booking.api.domain.dto;

import com.hackaton.booking.api.domain.dto.response.ClientResponseDTO;
import com.hackaton.booking.api.domain.dto.response.LocationResponseDTO;
import lombok.Data;

@Data
public class MailDTO {

    private String from;
    private String password;
    private String subject;
    private ClientResponseDTO clientInfos;
    private LocationResponseDTO params;
}
