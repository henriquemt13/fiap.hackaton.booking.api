package com.hackaton.booking.api.domain.dto.request;

import com.hackaton.booking.api.domain.enums.AddOnTypeEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddOnRequestDTO {

    @NotNull(message = "type should not be null")
    private AddOnTypeEnum type;
    @NotNull(message = "description should not be null")
    @NotEmpty(message = "description should not be null")
    private String description;
    @NotNull(message = "totalValue should not be null")
    private BigDecimal totalValue;
}
