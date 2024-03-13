package com.tech.challenge.parking.meter.api.domain.mapper;

import com.tech.challenge.parking.meter.api.domain.dto.request.UpdateParquimetroDTO;
import com.tech.challenge.parking.meter.api.domain.dto.response.ParquimetroResponseDTO;
import com.tech.challenge.parking.meter.api.domain.model.Parquimetro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, componentModel = "spring")
public interface ParquimetroMapper {

    ParquimetroResponseDTO of(Parquimetro parquimetro);

    List<ParquimetroResponseDTO> of(List<Parquimetro> parquimetro);

    Parquimetro of(UpdateParquimetroDTO updateParquimetroDTO);

    @Mapping(target = "id", ignore = true)
    Parquimetro of(UpdateParquimetroDTO dto, @MappingTarget Parquimetro eletrodomestico);
}
