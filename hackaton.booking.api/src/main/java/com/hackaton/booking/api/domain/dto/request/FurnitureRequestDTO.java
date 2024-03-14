package com.hackaton.booking.api.domain.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FurnitureRequestDTO {

    private Long id;
    @NotNull(message = "quantity should not be null")
    @NotEmpty(message = "quantity should not be null")
    private String name;
    @NotNull(message = "quantity should not be null")
    @NotEmpty(message = "quantity should not be null")
    private Long quantity;
}
