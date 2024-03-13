package com.tech.challenge.parking.meter.api.domain.mapper;

import com.tech.challenge.parking.meter.api.domain.dto.request.UpdateVeiculoDTO;
import com.tech.challenge.parking.meter.api.domain.dto.request.VeiculoRequestDTO;
import com.tech.challenge.parking.meter.api.domain.dto.response.VeiculoResponseDTO;
import com.tech.challenge.parking.meter.api.domain.model.Veiculo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-02T20:13:21-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class VeiculoMapperImpl implements VeiculoMapper {

    @Override
    public Veiculo of(VeiculoRequestDTO requestDTO) {
        if ( requestDTO == null ) {
            return null;
        }

        Veiculo veiculo = new Veiculo();

        veiculo.setPlaca( requestDTO.getPlaca() );
        veiculo.setTipoVeiculo( requestDTO.getTipoVeiculo() );
        veiculo.setModelo( requestDTO.getModelo() );
        veiculo.setCor( requestDTO.getCor() );
        veiculo.setAno( requestDTO.getAno() );
        veiculo.setNomeDono( requestDTO.getNomeDono() );
        veiculo.setDocumentoDono( requestDTO.getDocumentoDono() );
        veiculo.setContatoDono( requestDTO.getContatoDono() );

        return veiculo;
    }

    @Override
    public VeiculoResponseDTO of(Veiculo veiculo) {
        if ( veiculo == null ) {
            return null;
        }

        VeiculoResponseDTO veiculoResponseDTO = new VeiculoResponseDTO();

        veiculoResponseDTO.setPlaca( veiculo.getPlaca() );
        veiculoResponseDTO.setTipoVeiculo( veiculo.getTipoVeiculo() );
        veiculoResponseDTO.setModelo( veiculo.getModelo() );
        veiculoResponseDTO.setCor( veiculo.getCor() );
        veiculoResponseDTO.setAno( veiculo.getAno() );
        veiculoResponseDTO.setNomeDono( veiculo.getNomeDono() );
        veiculoResponseDTO.setDocumentoDono( veiculo.getDocumentoDono() );
        veiculoResponseDTO.setContatoDono( veiculo.getContatoDono() );

        return veiculoResponseDTO;
    }

    @Override
    public List<VeiculoResponseDTO> of(List<Veiculo> veiculo) {
        if ( veiculo == null ) {
            return null;
        }

        List<VeiculoResponseDTO> list = new ArrayList<VeiculoResponseDTO>( veiculo.size() );
        for ( Veiculo veiculo1 : veiculo ) {
            list.add( of( veiculo1 ) );
        }

        return list;
    }

    @Override
    public Veiculo of(UpdateVeiculoDTO requestDTO, Veiculo veiculo) {
        if ( requestDTO == null ) {
            return veiculo;
        }

        if ( requestDTO.getTipoVeiculo() != null ) {
            veiculo.setTipoVeiculo( requestDTO.getTipoVeiculo() );
        }
        if ( requestDTO.getModelo() != null ) {
            veiculo.setModelo( requestDTO.getModelo() );
        }
        if ( requestDTO.getCor() != null ) {
            veiculo.setCor( requestDTO.getCor() );
        }
        if ( requestDTO.getAno() != null ) {
            veiculo.setAno( requestDTO.getAno() );
        }
        if ( requestDTO.getContatoDono() != null ) {
            veiculo.setContatoDono( requestDTO.getContatoDono() );
        }

        return veiculo;
    }
}
