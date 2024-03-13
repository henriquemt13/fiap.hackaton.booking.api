package com.tech.challenge.parking.meter.api.domain.mapper;

import com.tech.challenge.parking.meter.api.domain.dto.response.ExtratoResponseDTO;
import com.tech.challenge.parking.meter.api.domain.model.Estacionamento;
import com.tech.challenge.parking.meter.api.domain.model.Extrato;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-02T20:13:21-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class ExtratoMapperImpl implements ExtratoMapper {

    @Override
    public ExtratoResponseDTO of(BigDecimal valorPago, Estacionamento estacionamento) {
        if ( valorPago == null && estacionamento == null ) {
            return null;
        }

        ExtratoResponseDTO extratoResponseDTO = new ExtratoResponseDTO();

        if ( estacionamento != null ) {
            extratoResponseDTO.setEntrada( estacionamento.getEntrada() );
            extratoResponseDTO.setSaida( estacionamento.getSaida() );
            extratoResponseDTO.setTempoContratado( estacionamento.getTempoContratado() );
        }
        extratoResponseDTO.setValorPago( valorPago );

        return extratoResponseDTO;
    }

    @Override
    public Extrato of(Estacionamento estacionamento, String placa) {
        if ( estacionamento == null && placa == null ) {
            return null;
        }

        Extrato.ExtratoBuilder extrato = Extrato.builder();

        if ( estacionamento != null ) {
            extrato.estacionamentoId( estacionamento.getId() );
            extrato.id( estacionamento.getId() );
            extrato.placa( estacionamento.getPlaca() );
            extrato.tempoContratado( estacionamento.getTempoContratado() );
            extrato.createdAt( estacionamento.getCreatedAt() );
            extrato.createdBy( estacionamento.getCreatedBy() );
            extrato.updatedAt( estacionamento.getUpdatedAt() );
            extrato.updatedBy( estacionamento.getUpdatedBy() );
        }

        return extrato.build();
    }
}
