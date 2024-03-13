package com.hackaton.booking.management.api.domain.mapper;

import com.hackaton.booking.management.api.domain.dto.request.AddOnRequestDTO;
import com.hackaton.booking.management.api.domain.dto.response.AddOnResponseDTO;
import com.hackaton.booking.management.api.domain.model.AddOn;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddOnMapper {

    AddOn of(AddOnRequestDTO requestDTO);

    AddOnResponseDTO of(AddOn addOn);

    List<AddOnResponseDTO> of(List<AddOn> addOn);
}
