package com.hackaton.booking.api.domain.mapper;

import com.hackaton.booking.api.domain.dto.response.BathroomResponseDTO;
import com.hackaton.booking.api.domain.model.Bathroom;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-13T22:29:22-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class BathroomMapperImpl implements BathroomMapper {

    @Override
    public BathroomResponseDTO of(Bathroom bathroom) {
        if ( bathroom == null ) {
            return null;
        }

        BathroomResponseDTO bathroomResponseDTO = new BathroomResponseDTO();

        bathroomResponseDTO.setType( setBathroomType( bathroom.getType() ) );
        bathroomResponseDTO.setId( bathroom.getId() );
        bathroomResponseDTO.setDescription( bathroom.getDescription() );
        bathroomResponseDTO.setCreatedAt( bathroom.getCreatedAt() );
        bathroomResponseDTO.setUpdatedAt( bathroom.getUpdatedAt() );

        return bathroomResponseDTO;
    }

    @Override
    public List<BathroomResponseDTO> of(List<Bathroom> bathroom) {
        if ( bathroom == null ) {
            return null;
        }

        List<BathroomResponseDTO> list = new ArrayList<BathroomResponseDTO>( bathroom.size() );
        for ( Bathroom bathroom1 : bathroom ) {
            list.add( of( bathroom1 ) );
        }

        return list;
    }
}
