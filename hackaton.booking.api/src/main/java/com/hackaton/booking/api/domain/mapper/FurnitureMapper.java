package com.hackaton.booking.api.domain.mapper;

import com.hackaton.booking.api.domain.dto.request.FurnitureRequestDTO;
import com.hackaton.booking.api.domain.dto.response.FurnitureResponseDTO;
import com.hackaton.booking.api.domain.model.Furniture;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FurnitureMapper {

    Furniture ofRequest(FurnitureRequestDTO requestDTO);

    List<Furniture> ofRequest(List<FurnitureRequestDTO> requestDTO);

    FurnitureResponseDTO of(Furniture furniture);

    List<FurnitureResponseDTO> of(List<Furniture> furniture);
}
