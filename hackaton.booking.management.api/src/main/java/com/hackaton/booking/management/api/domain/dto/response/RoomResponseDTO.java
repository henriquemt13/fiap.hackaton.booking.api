package com.hackaton.booking.management.api.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hackaton.booking.management.api.domain.enums.RoomTypeEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomResponseDTO {

    private Long id;
    private Long idBuilding;
    private RoomTypeEnum type;
    private Long maxCapacity;
    private Long totalBeds;
    private Long totalRooms;
    private BigDecimal totalDailyValue;
    private BathroomResponseDTO bathroom;
    private List<FurnitureResponseDTO> furniture;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
