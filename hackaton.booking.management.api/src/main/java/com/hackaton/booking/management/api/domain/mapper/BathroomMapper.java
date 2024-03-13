package com.hackaton.booking.management.api.domain.mapper;

import com.hackaton.booking.management.api.domain.dto.request.BathroomRequestDTO;
import com.hackaton.booking.management.api.domain.dto.response.BathroomResponseDTO;
import com.hackaton.booking.management.api.domain.model.Bathroom;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BathroomMapper {

    Bathroom of(BathroomRequestDTO requestDTO);

    BathroomResponseDTO of(Bathroom bathroom);

    List<BathroomResponseDTO> of(List<Bathroom> bathroom);
}
