package com.tech.challenge.parking.meter.api.domain.mapper;

import com.tech.challenge.parking.meter.api.domain.dto.response.ExtratoResponseDTO;
import com.tech.challenge.parking.meter.api.domain.model.Estacionamento;
import com.tech.challenge.parking.meter.api.domain.model.Extrato;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.math.BigDecimal;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, componentModel = "spring")
public interface ExtratoMapper {

    ExtratoResponseDTO of(BigDecimal valorPago, Estacionamento estacionamento);

    @Mapping(target = "estacionamentoId", source = "estacionamento.id")
    Extrato of(Estacionamento estacionamento, String placa);

}
