package com.hackaton.booking.management.api.domain.dto.response;

import com.hackaton.booking.management.api.domain.enums.AddOnTypeEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class AddOnResponseDTO {

    private Long id;
    private AddOnTypeEnum type;
    private String description;
    private BigDecimal totalValue;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
