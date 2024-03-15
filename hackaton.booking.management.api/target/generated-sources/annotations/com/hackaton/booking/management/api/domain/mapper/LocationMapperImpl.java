package com.hackaton.booking.management.api.domain.mapper;

import com.hackaton.booking.management.api.domain.dto.request.LocationRequestDTO;
import com.hackaton.booking.management.api.domain.dto.response.LocationResponseDTO;
import com.hackaton.booking.management.api.domain.model.Location;
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
public class LocationMapperImpl implements LocationMapper {

    @Override
    public Location of(LocationRequestDTO requestDTO) {
        if ( requestDTO == null ) {
            return null;
        }

        Location location = new Location();

        location.setName( requestDTO.getName() );
        location.setStreet( requestDTO.getStreet() );
        location.setCep( requestDTO.getCep() );
        location.setCity( requestDTO.getCity() );
        location.setState( requestDTO.getState() );

        return location;
    }

    @Override
    public LocationResponseDTO of(Location location) {
        if ( location == null ) {
            return null;
        }

        LocationResponseDTO locationResponseDTO = new LocationResponseDTO();

        locationResponseDTO.setId( location.getId() );
        locationResponseDTO.setName( location.getName() );
        locationResponseDTO.setStreet( location.getStreet() );
        locationResponseDTO.setCep( location.getCep() );
        locationResponseDTO.setCity( location.getCity() );
        locationResponseDTO.setState( location.getState() );
        locationResponseDTO.setCreatedAt( location.getCreatedAt() );
        locationResponseDTO.setUpdatedAt( location.getUpdatedAt() );

        return locationResponseDTO;
    }

    @Override
    public List<LocationResponseDTO> of(List<Location> location) {
        if ( location == null ) {
            return null;
        }

        List<LocationResponseDTO> list = new ArrayList<LocationResponseDTO>( location.size() );
        for ( Location location1 : location ) {
            list.add( of( location1 ) );
        }

        return list;
    }
}
