package com.hackaton.booking.management.api.domain.mapper;

import com.hackaton.booking.management.api.domain.dto.request.LocationRequestDTO;
import com.hackaton.booking.management.api.domain.dto.response.LocationResponseDTO;
import com.hackaton.booking.management.api.domain.model.Location;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    Location of(LocationRequestDTO requestDTO);

    LocationResponseDTO of(Location location);

    List<LocationResponseDTO> of(List<Location> location);
}
