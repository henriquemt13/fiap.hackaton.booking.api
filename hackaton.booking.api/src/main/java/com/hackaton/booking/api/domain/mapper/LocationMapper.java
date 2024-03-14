package com.hackaton.booking.api.domain.mapper;

import com.hackaton.booking.api.domain.dto.request.LocationRequestDTO;
import com.hackaton.booking.api.domain.dto.request.BookingFilterRequestDTO;
import com.hackaton.booking.api.domain.dto.response.LocationResponseDTO;
import com.hackaton.booking.api.domain.model.Location;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    Location of(BookingFilterRequestDTO requestDTO);

    Location of(LocationRequestDTO requestDTO);

    LocationResponseDTO of(Location location);

    List<LocationResponseDTO> of(List<Location> location);
}
