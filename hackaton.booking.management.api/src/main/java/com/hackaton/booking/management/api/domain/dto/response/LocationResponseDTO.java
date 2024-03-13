package com.hackaton.booking.management.api.domain.dto.response;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class LocationResponseDTO {

    private Long id;
    private String name;
    private String street;
    private Long cep;
    private String city;
    private String state;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
