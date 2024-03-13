package com.hackaton.booking.management.api.domain.dto.request;

import com.hackaton.booking.management.api.domain.enums.BathroomTypeEnum;
import com.hackaton.booking.management.api.domain.enums.RoomTypeEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class RoomRequestDTO {

    @NotNull
    private Long idBuilding;
    @NotNull
    private RoomTypeEnum type;
    @NotNull
    private Long maxCapacity;
    @NotNull
    private Long totalBeds;
    @NotNull
    private Long totalRooms;
    @NotNull
    private BigDecimal totalDailyValue;
    private BathroomTypeEnum bathroomType;
    private List<FurnitureRequestDTO> furniture;

}
