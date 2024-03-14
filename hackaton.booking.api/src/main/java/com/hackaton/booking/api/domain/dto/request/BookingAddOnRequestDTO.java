package com.hackaton.booking.api.domain.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookingAddOnRequestDTO {

    @NotNull(message = "idBooking should not be null")
    private Long idBooking;
    @NotNull(message = "idAddOn should not be null")
    private Long idAddOn;
}
