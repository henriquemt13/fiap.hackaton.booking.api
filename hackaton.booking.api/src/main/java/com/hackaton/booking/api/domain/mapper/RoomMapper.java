package com.hackaton.booking.api.domain.mapper;

import com.hackaton.booking.api.domain.dto.request.RoomRequestDTO;
import com.hackaton.booking.api.domain.dto.request.BookingFilterRequestDTO;
import com.hackaton.booking.api.domain.dto.response.RoomResponseDTO;
import com.hackaton.booking.api.domain.enums.RoomTypeEnum;
import com.hackaton.booking.api.domain.model.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    @Mapping(source = "type", target = "type", qualifiedByName = "getRoomTypeEnum")
    Room of(RoomRequestDTO requestDTO);

    @Mapping(source = "type", target = "type", qualifiedByName = "getRoomTypeEnum")
    Room of(BookingFilterRequestDTO requestDTO);

    @Mapping(source = "type", target = "type", qualifiedByName = "setRoomTypeEnum")
    RoomResponseDTO of(Room room);

    List<RoomResponseDTO> of(List<Room> room);

    @Named("getRoomTypeEnum")
    default String getRoomTypeEnum(RoomTypeEnum roomTypeEnum) {
        if (roomTypeEnum != null) {
            return roomTypeEnum.name();
        }
        return null;
    }

    @Named("setRoomTypeEnum")
    default RoomTypeEnum setRoomTypeEnum(String roomTypeEnum) {
        return RoomTypeEnum.valueOf(roomTypeEnum);
    }

}
