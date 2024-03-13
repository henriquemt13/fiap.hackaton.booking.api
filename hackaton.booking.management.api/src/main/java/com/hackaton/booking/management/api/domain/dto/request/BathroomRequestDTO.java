package com.hackaton.booking.management.api.domain.dto.request;

import com.hackaton.booking.management.api.domain.enums.BathroomTypeEnum;
import jakarta.validation.constraints.NotNull;

public class BathroomRequestDTO {
    @NotNull(message = "type should not be null")
    private BathroomTypeEnum type;
}
