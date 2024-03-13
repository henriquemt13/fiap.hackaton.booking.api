package com.hackaton.booking.management.api.domain.mapper;

import com.hackaton.booking.management.api.domain.dto.request.FurnitureRequestDTO;
import com.hackaton.booking.management.api.domain.dto.response.FurnitureResponseDTO;
import com.hackaton.booking.management.api.domain.model.Furniture;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FurnitureMapper {

    Furniture ofRequest(FurnitureRequestDTO requestDTO);

    List<Furniture> ofRequest(List<FurnitureRequestDTO> requestDTO);

    FurnitureResponseDTO of(Furniture furniture);

    List<FurnitureResponseDTO> of(List<Furniture> furniture);
}
