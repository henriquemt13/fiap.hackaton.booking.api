package com.hackaton.booking.management.api.domain.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class LocationRequestDTO {
    @NotNull(message = "name should not be null")
    @NotEmpty(message = "name should not be null")
    private String name;
    @NotNull(message = "street should not be null")
    @NotEmpty(message = "street should not be null")
    private String street;
    @NotNull(message = "cep should not be null")
    @NotEmpty(message = "cep should not be null")
    private Long cep;
    @NotNull(message = "city should not be null")
    @NotEmpty(message = "city should not be null")
    private String city;
    @NotNull(message = "state should not be null")
    @NotEmpty(message = "state should not be null")
    private String state;
    private List<AmenityRequestDTO> amenities;

}
