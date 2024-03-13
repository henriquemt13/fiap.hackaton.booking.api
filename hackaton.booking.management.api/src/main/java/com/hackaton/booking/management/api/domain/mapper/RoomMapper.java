package com.hackaton.booking.management.api.domain.mapper;

import com.hackaton.booking.management.api.domain.dto.request.RoomRequestDTO;
import com.hackaton.booking.management.api.domain.dto.response.RoomResponseDTO;
import com.hackaton.booking.management.api.domain.model.Room;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    Room of(RoomRequestDTO requestDTO);

    RoomResponseDTO of(Room room);

    List<RoomResponseDTO> of(List<Room> room);
}
