package com.hackaton.booking.management.api.domain.mapper;

import com.hackaton.booking.management.api.domain.dto.request.AmenityRequestDTO;
import com.hackaton.booking.management.api.domain.dto.response.AmenityResponseDTO;
import com.hackaton.booking.management.api.domain.model.Amenity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AmenityMapper {

    Amenity ofRequest(AmenityRequestDTO requestDTO);

    List<Amenity> ofRequest(List<AmenityRequestDTO> amenities);

    AmenityResponseDTO of(Amenity amenity);

    List<AmenityResponseDTO> of(List<Amenity> amenity);
}
