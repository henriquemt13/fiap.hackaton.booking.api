package com.hackaton.booking.api.domain.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
public class ClientResponseDTO {

    private Long id;
    private String cpf;
    private String passport;
    private String fullName;
    private LocalDate birthdayDate;
    private String nationality;
    private String homeCountryAddress;
    private String phone;
    private String email;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
