package com.hackaton.booking.management.api.domain.dto.response;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class LocationResponseDTO {

    private Long id;
    private String name;
    private String street;
    private String cep;
    private String city;
    private String state;
    private List<AmenityResponseDTO> amenities;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
