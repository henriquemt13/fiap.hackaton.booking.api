package com.hackaton.booking.api.domain.dto.response;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class BookingAddOnResponseDTO {

    private Long id;
    private Long idBooking;
    private Long idAddOn;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
