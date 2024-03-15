package com.hackaton.booking.management.api.domain.mapper;

import com.hackaton.booking.management.api.domain.dto.request.FurnitureRequestDTO;
import com.hackaton.booking.management.api.domain.dto.response.FurnitureResponseDTO;
import com.hackaton.booking.management.api.domain.model.Furniture;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-15T07:59:45-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class FurnitureMapperImpl implements FurnitureMapper {

    @Override
    public Furniture ofRequest(FurnitureRequestDTO requestDTO) {
        if ( requestDTO == null ) {
            return null;
        }

        Furniture furniture = new Furniture();

        furniture.setId( requestDTO.getId() );
        furniture.setName( requestDTO.getName() );
        furniture.setQuantity( requestDTO.getQuantity() );

        return furniture;
    }

    @Override
    public List<Furniture> ofRequest(List<FurnitureRequestDTO> requestDTO) {
        if ( requestDTO == null ) {
            return null;
        }

        List<Furniture> list = new ArrayList<Furniture>( requestDTO.size() );
        for ( FurnitureRequestDTO furnitureRequestDTO : requestDTO ) {
            list.add( ofRequest( furnitureRequestDTO ) );
        }

        return list;
    }

    @Override
    public FurnitureResponseDTO of(Furniture furniture) {
        if ( furniture == null ) {
            return null;
        }

        FurnitureResponseDTO furnitureResponseDTO = new FurnitureResponseDTO();

        furnitureResponseDTO.setId( furniture.getId() );
        furnitureResponseDTO.setName( furniture.getName() );
        furnitureResponseDTO.setQuantity( furniture.getQuantity() );
        furnitureResponseDTO.setCreatedAt( furniture.getCreatedAt() );
        furnitureResponseDTO.setUpdatedAt( furniture.getUpdatedAt() );

        return furnitureResponseDTO;
    }

    @Override
    public List<FurnitureResponseDTO> of(List<Furniture> furniture) {
        if ( furniture == null ) {
            return null;
        }

        List<FurnitureResponseDTO> list = new ArrayList<FurnitureResponseDTO>( furniture.size() );
        for ( Furniture furniture1 : furniture ) {
            list.add( of( furniture1 ) );
        }

        return list;
    }
}
