package com.hackaton.booking.management.api.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BuildingResponseDTO {

    private Long id;
    private Long idLocation;
    private String name;
    private List<RoomResponseDTO> rooms;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
