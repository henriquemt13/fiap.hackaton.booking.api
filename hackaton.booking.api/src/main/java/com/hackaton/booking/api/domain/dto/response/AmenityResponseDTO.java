package com.hackaton.booking.api.domain.dto.response;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class AmenityResponseDTO {

    private Long id;
    private String name;
    private Long quantity;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
