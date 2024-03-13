package com.hackaton.booking.management.api.domain.mapper;

import com.hackaton.booking.management.api.domain.dto.request.BuildingRequestDTO;
import com.hackaton.booking.management.api.domain.dto.response.BuildingResponseDTO;
import com.hackaton.booking.management.api.domain.model.Building;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BuildingMapper {

    Building of(BuildingRequestDTO requestDTO);

    BuildingResponseDTO of(Building building);

    List<BuildingResponseDTO> of(List<Building> building);
}
