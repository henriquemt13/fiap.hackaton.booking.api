package com.hackaton.booking.api.domain.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class LocationRequestDTO {
    private String name;
    private String street;
    private String cep;
    private String city;
    private String state;

}
