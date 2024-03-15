package com.hackaton.booking.management.api.domain.mapper;

import com.hackaton.booking.management.api.domain.dto.request.RoomRequestDTO;
import com.hackaton.booking.management.api.domain.dto.response.RoomResponseDTO;
import com.hackaton.booking.management.api.domain.enums.BathroomTypeEnum;
import com.hackaton.booking.management.api.domain.enums.RoomTypeEnum;
import com.hackaton.booking.management.api.domain.model.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    @Mapping(source = "type", target = "type", qualifiedByName = "getRoomTypeEnum")
    Room of(RoomRequestDTO requestDTO);

    @Mapping(source = "type", target = "type", qualifiedByName = "setRoomTypeEnum")
    RoomResponseDTO of(Room room);

    List<RoomResponseDTO> of(List<Room> room);

    @Named("getRoomTypeEnum")
    default String getRoomTypeEnum(RoomTypeEnum roomTypeEnum) {
        return roomTypeEnum.name();
    }

    @Named("setRoomTypeEnum")
    default RoomTypeEnum setRoomTypeEnum(String roomTypeEnum) {
        return RoomTypeEnum.valueOf(roomTypeEnum);
    }

}
