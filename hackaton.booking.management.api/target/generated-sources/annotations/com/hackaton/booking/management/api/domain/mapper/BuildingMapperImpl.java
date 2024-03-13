package com.hackaton.booking.management.api.domain.mapper;

import com.hackaton.booking.management.api.domain.dto.request.BuildingRequestDTO;
import com.hackaton.booking.management.api.domain.dto.response.BuildingResponseDTO;
import com.hackaton.booking.management.api.domain.model.Building;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-12T20:15:05-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class BuildingMapperImpl implements BuildingMapper {

    @Override
    public Building of(BuildingRequestDTO requestDTO) {
        if ( requestDTO == null ) {
            return null;
        }

        Building building = new Building();

        return building;
    }

    @Override
    public BuildingResponseDTO of(Building building) {
        if ( building == null ) {
            return null;
        }

        BuildingResponseDTO buildingResponseDTO = new BuildingResponseDTO();

        return buildingResponseDTO;
    }

    @Override
    public List<BuildingResponseDTO> of(List<Building> building) {
        if ( building == null ) {
            return null;
        }

        List<BuildingResponseDTO> list = new ArrayList<BuildingResponseDTO>( building.size() );
        for ( Building building1 : building ) {
            list.add( of( building1 ) );
        }

        return list;
    }
}
