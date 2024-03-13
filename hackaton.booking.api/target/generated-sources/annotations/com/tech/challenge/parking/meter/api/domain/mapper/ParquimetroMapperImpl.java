package com.tech.challenge.parking.meter.api.domain.mapper;

import com.tech.challenge.parking.meter.api.domain.dto.request.UpdateParquimetroDTO;
import com.tech.challenge.parking.meter.api.domain.dto.response.ParquimetroResponseDTO;
import com.tech.challenge.parking.meter.api.domain.model.Parquimetro;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-02T20:13:20-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class ParquimetroMapperImpl implements ParquimetroMapper {

    @Override
    public ParquimetroResponseDTO of(Parquimetro parquimetro) {
        if ( parquimetro == null ) {
            return null;
        }

        ParquimetroResponseDTO parquimetroResponseDTO = new ParquimetroResponseDTO();

        parquimetroResponseDTO.setPrecoInicial( parquimetro.getPrecoInicial() );
        parquimetroResponseDTO.setTotalVagasCidade( parquimetro.getTotalVagasCidade() );

        return parquimetroResponseDTO;
    }

    @Override
    public List<ParquimetroResponseDTO> of(List<Parquimetro> parquimetro) {
        if ( parquimetro == null ) {
            return null;
        }

        List<ParquimetroResponseDTO> list = new ArrayList<ParquimetroResponseDTO>( parquimetro.size() );
        for ( Parquimetro parquimetro1 : parquimetro ) {
            list.add( of( parquimetro1 ) );
        }

        return list;
    }

    @Override
    public Parquimetro of(UpdateParquimetroDTO updateParquimetroDTO) {
        if ( updateParquimetroDTO == null ) {
            return null;
        }

        Parquimetro parquimetro = new Parquimetro();

        parquimetro.setTotalVagasCidade( updateParquimetroDTO.getTotalVagasCidade() );
        parquimetro.setPrecoInicial( updateParquimetroDTO.getPrecoInicial() );

        return parquimetro;
    }

    @Override
    public Parquimetro of(UpdateParquimetroDTO dto, Parquimetro eletrodomestico) {
        if ( dto == null ) {
            return eletrodomestico;
        }

        if ( dto.getTotalVagasCidade() != null ) {
            eletrodomestico.setTotalVagasCidade( dto.getTotalVagasCidade() );
        }
        if ( dto.getPrecoInicial() != null ) {
            eletrodomestico.setPrecoInicial( dto.getPrecoInicial() );
        }

        return eletrodomestico;
    }
}
