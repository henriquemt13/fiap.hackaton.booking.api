package com.hackaton.booking.management.api.domain.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BuildingRequestDTO {

    @NotNull(message = "id_location should not be null")
    private Long idLocation;
    @NotNull(message = "name should not be null")
    @NotEmpty(message = "name should not be null")
    private String name;
}
