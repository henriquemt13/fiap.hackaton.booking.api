package com.hackaton.booking.management.api.domain.mapper;

import com.hackaton.booking.management.api.domain.dto.request.AddOnRequestDTO;
import com.hackaton.booking.management.api.domain.dto.response.AddOnResponseDTO;
import com.hackaton.booking.management.api.domain.enums.AddOnTypeEnum;
import com.hackaton.booking.management.api.domain.model.AddOn;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddOnMapper {

    @Mapping(source = "type", target = "type", qualifiedByName = "getAddOnType")
    AddOn of(AddOnRequestDTO requestDTO);

    @Mapping(source = "type", target = "type", qualifiedByName = "setAddOnType")
    AddOnResponseDTO of(AddOn addOn);

    List<AddOnResponseDTO> of(List<AddOn> addOn);

    @Named("getAddOnType")
    default String getAddOnType(AddOnTypeEnum addOnTypeEnumn) {
        return addOnTypeEnumn.name();
    }

    @Named("setAddOnType")
    default AddOnTypeEnum setAddOnType(String addOnType) {
        return AddOnTypeEnum.valueOf(addOnType);
    }
}
