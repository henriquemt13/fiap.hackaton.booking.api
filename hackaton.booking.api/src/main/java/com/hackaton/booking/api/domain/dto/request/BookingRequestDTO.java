package com.hackaton.booking.api.domain.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class BookingRequestDTO {

    @NotNull(message = "idRoom should not be null")
    private Long idRoom;
    @NotNull(message = "idClient should not be null")
    private Long idClient;
    @NotNull(message = "startDate should not be null")
    private LocalDate startDate;
    @NotNull(message = "endDate should not be null")
    private LocalDate endDate;
    List<BookingAddOnRequestDTO> addOns;
}
