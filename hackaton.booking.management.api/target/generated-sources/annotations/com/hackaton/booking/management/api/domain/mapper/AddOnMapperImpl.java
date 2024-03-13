package com.hackaton.booking.management.api.domain.mapper;

import com.hackaton.booking.management.api.domain.dto.request.AddOnRequestDTO;
import com.hackaton.booking.management.api.domain.dto.response.AddOnResponseDTO;
import com.hackaton.booking.management.api.domain.model.AddOn;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-13T15:33:49-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class AddOnMapperImpl implements AddOnMapper {

    @Override
    public AddOn of(AddOnRequestDTO requestDTO) {
        if ( requestDTO == null ) {
            return null;
        }

        AddOn addOn = new AddOn();

        addOn.setType( getAddOnType( requestDTO.getType() ) );
        addOn.setDescription( requestDTO.getDescription() );
        addOn.setTotalValue( requestDTO.getTotalValue() );

        return addOn;
    }

    @Override
    public AddOnResponseDTO of(AddOn addOn) {
        if ( addOn == null ) {
            return null;
        }

        AddOnResponseDTO addOnResponseDTO = new AddOnResponseDTO();

        addOnResponseDTO.setType( setAddOnType( addOn.getType() ) );
        addOnResponseDTO.setId( addOn.getId() );
        addOnResponseDTO.setDescription( addOn.getDescription() );
        addOnResponseDTO.setTotalValue( addOn.getTotalValue() );
        addOnResponseDTO.setCreatedAt( addOn.getCreatedAt() );
        addOnResponseDTO.setUpdatedAt( addOn.getUpdatedAt() );

        return addOnResponseDTO;
    }

    @Override
    public List<AddOnResponseDTO> of(List<AddOn> addOn) {
        if ( addOn == null ) {
            return null;
        }

        List<AddOnResponseDTO> list = new ArrayList<AddOnResponseDTO>( addOn.size() );
        for ( AddOn addOn1 : addOn ) {
            list.add( of( addOn1 ) );
        }

        return list;
    }
}
