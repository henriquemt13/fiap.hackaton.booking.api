package com.hackaton.booking.api.domain.dto.response;

import lombok.Data;

import java.math.BigDecimal;
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
    private List<BuildingResponseDTO> buildings;
    private List<AddOnResponseDTO> addOns;
    private BookingResponseDTO booking;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
