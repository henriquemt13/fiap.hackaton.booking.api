package com.hackaton.booking.api.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hackaton.booking.api.domain.enums.RoomTypeEnum;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingFilterRequestDTO {

    private String city;
    private String state;
    private RoomTypeEnum type;
    @JsonProperty(value = "expectedCapacity")
    private Long maxCapacity;
    private Long totalBeds;
    private Long totalDailyValue;
    @NotNull(message = "start should not be null")
    @FutureOrPresent
    private LocalDate startDate;
    @Future
    @NotNull(message = "endDate should not be null")
    private LocalDate endDate;
}
