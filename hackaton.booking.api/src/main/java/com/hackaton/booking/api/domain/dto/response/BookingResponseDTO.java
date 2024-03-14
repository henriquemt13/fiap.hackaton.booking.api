package com.hackaton.booking.api.domain.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Data
public class BookingResponseDTO {

    private Long id;
    private Long idRoom;
    private Long idClient;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<BookingAddOnResponseDTO> addOns;
    private BigDecimal totalValue;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
