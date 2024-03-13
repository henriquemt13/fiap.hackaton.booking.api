package com.hackaton.booking.management.api.domain.mapper;

import com.hackaton.booking.management.api.domain.dto.request.RoomRequestDTO;
import com.hackaton.booking.management.api.domain.dto.response.RoomResponseDTO;
import com.hackaton.booking.management.api.domain.model.Room;
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
public class RoomMapperImpl implements RoomMapper {

    @Override
    public Room of(RoomRequestDTO requestDTO) {
        if ( requestDTO == null ) {
            return null;
        }

        Room room = new Room();

        room.setType( getRoomTypeEnum( requestDTO.getType() ) );
        room.setIdBuilding( requestDTO.getIdBuilding() );
        room.setMaxCapacity( requestDTO.getMaxCapacity() );
        room.setTotalBeds( requestDTO.getTotalBeds() );
        room.setTotalRooms( requestDTO.getTotalRooms() );
        room.setTotalDailyValue( requestDTO.getTotalDailyValue() );
        if ( requestDTO.getBathroomType() != null ) {
            room.setBathroomType( requestDTO.getBathroomType().name() );
        }

        return room;
    }

    @Override
    public RoomResponseDTO of(Room room) {
        if ( room == null ) {
            return null;
        }

        RoomResponseDTO roomResponseDTO = new RoomResponseDTO();

        roomResponseDTO.setType( setRoomTypeEnum( room.getType() ) );
        roomResponseDTO.setId( room.getId() );
        roomResponseDTO.setIdBuilding( room.getIdBuilding() );
        roomResponseDTO.setMaxCapacity( room.getMaxCapacity() );
        roomResponseDTO.setTotalBeds( room.getTotalBeds() );
        roomResponseDTO.setTotalRooms( room.getTotalRooms() );
        roomResponseDTO.setTotalDailyValue( room.getTotalDailyValue() );
        roomResponseDTO.setCreatedAt( room.getCreatedAt() );
        roomResponseDTO.setUpdatedAt( room.getUpdatedAt() );

        return roomResponseDTO;
    }

    @Override
    public List<RoomResponseDTO> of(List<Room> room) {
        if ( room == null ) {
            return null;
        }

        List<RoomResponseDTO> list = new ArrayList<RoomResponseDTO>( room.size() );
        for ( Room room1 : room ) {
            list.add( of( room1 ) );
        }

        return list;
    }
}
