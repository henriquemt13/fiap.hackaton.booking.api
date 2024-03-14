package com.hackaton.booking.api.domain.dto.request;

import com.hackaton.booking.api.validator.Document;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientRequestDTO {
    @NotNull(message = "cpf should not be null")
    @NotEmpty(message = "cpf should not be empty")
    @Document
    private String cpf;
    @Size(min = 6, max = 6)
    private String passport;
    @NotNull(message = "fullName should not be null")
    @NotEmpty(message = "fullName should not be empty")
    private String fullName;
    @NotNull(message = "birthdayDate should not be null")
    @Past
    private LocalDate birthdayDate;
    @NotNull(message = "nationality should not be null")
    @NotEmpty(message = "nationality should not be empty")
    private String nationality;
    @NotNull(message = "homeCountryAddress should not be null")
    @NotEmpty(message = "homeCountryAddress should not be empty")
    private String homeCountryAddress;
    @NotNull(message = "phone should not be null")
    @NotEmpty(message = "phone should not be empty")
    private String phone;
    @NotNull(message = "email should not be null")
    @NotEmpty(message = "email should not be empty")
    private String email;
}
