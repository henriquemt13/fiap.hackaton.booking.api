package com.hackaton.booking.management.api.domain.dto.response;

import com.hackaton.booking.management.api.domain.enums.BathroomTypeEnum;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class BathroomResponseDTO {

    private Long id;
    private BathroomTypeEnum type;
    private String description;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
