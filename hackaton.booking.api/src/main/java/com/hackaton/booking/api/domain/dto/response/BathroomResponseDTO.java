package com.hackaton.booking.api.domain.dto.response;

import com.hackaton.booking.api.domain.enums.BathroomTypeEnum;
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
