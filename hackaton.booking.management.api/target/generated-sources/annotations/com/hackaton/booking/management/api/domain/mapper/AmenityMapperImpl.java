package com.hackaton.booking.management.api.domain.mapper;

import com.hackaton.booking.management.api.domain.dto.request.AmenityRequestDTO;
import com.hackaton.booking.management.api.domain.dto.response.AmenityResponseDTO;
import com.hackaton.booking.management.api.domain.model.Amenity;
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
public class AmenityMapperImpl implements AmenityMapper {

    @Override
    public Amenity ofRequest(AmenityRequestDTO requestDTO) {
        if ( requestDTO == null ) {
            return null;
        }

        Amenity amenity = new Amenity();

        amenity.setId( requestDTO.getId() );
        amenity.setName( requestDTO.getName() );
        amenity.setQuantity( requestDTO.getQuantity() );

        return amenity;
    }

    @Override
    public List<Amenity> ofRequest(List<AmenityRequestDTO> amenities) {
        if ( amenities == null ) {
            return null;
        }

        List<Amenity> list = new ArrayList<Amenity>( amenities.size() );
        for ( AmenityRequestDTO amenityRequestDTO : amenities ) {
            list.add( ofRequest( amenityRequestDTO ) );
        }

        return list;
    }

    @Override
    public AmenityResponseDTO of(Amenity amenity) {
        if ( amenity == null ) {
            return null;
        }

        AmenityResponseDTO amenityResponseDTO = new AmenityResponseDTO();

        amenityResponseDTO.setId( amenity.getId() );
        amenityResponseDTO.setName( amenity.getName() );
        amenityResponseDTO.setQuantity( amenity.getQuantity() );
        amenityResponseDTO.setCreatedAt( amenity.getCreatedAt() );
        amenityResponseDTO.setUpdatedAt( amenity.getUpdatedAt() );

        return amenityResponseDTO;
    }

    @Override
    public List<AmenityResponseDTO> of(List<Amenity> amenity) {
        if ( amenity == null ) {
            return null;
        }

        List<AmenityResponseDTO> list = new ArrayList<AmenityResponseDTO>( amenity.size() );
        for ( Amenity amenity1 : amenity ) {
            list.add( of( amenity1 ) );
        }

        return list;
    }
}
