package com.hackaton.booking.management.api.domain.mapper;

import com.hackaton.booking.management.api.domain.dto.response.BathroomResponseDTO;
import com.hackaton.booking.management.api.domain.enums.BathroomTypeEnum;
import com.hackaton.booking.management.api.domain.model.Bathroom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BathroomMapper {

    @Mapping(source = "type", target = "type", qualifiedByName = "setBathroomType")
    BathroomResponseDTO of(Bathroom bathroom);

    List<BathroomResponseDTO> of(List<Bathroom> bathroom);

    @Named("setBathroomType")
    default BathroomTypeEnum setBathroomType(String bathroomTypeEnum) {
        return BathroomTypeEnum.valueOf(bathroomTypeEnum);
    }
}
