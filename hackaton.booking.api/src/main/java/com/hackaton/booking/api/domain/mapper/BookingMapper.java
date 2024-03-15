package com.hackaton.booking.api.domain.mapper;

import com.hackaton.booking.api.domain.dto.request.AmenityRequestDTO;
import com.hackaton.booking.api.domain.dto.request.BookingFilterRequestDTO;
import com.hackaton.booking.api.domain.dto.response.AddOnResponseDTO;
import com.hackaton.booking.api.domain.dto.response.AmenityResponseDTO;
import com.hackaton.booking.api.domain.dto.response.BathroomResponseDTO;
import com.hackaton.booking.api.domain.dto.response.BookingResponseDTO;
import com.hackaton.booking.api.domain.dto.request.BookingRequestDTO;
import com.hackaton.booking.api.domain.dto.response.BuildingResponseDTO;
import com.hackaton.booking.api.domain.dto.response.FurnitureResponseDTO;
import com.hackaton.booking.api.domain.dto.response.LocationResponseDTO;
import com.hackaton.booking.api.domain.dto.response.RoomResponseDTO;
import com.hackaton.booking.api.domain.enums.AddOnTypeEnum;
import com.hackaton.booking.api.domain.enums.BathroomTypeEnum;
import com.hackaton.booking.api.domain.enums.RoomTypeEnum;
import com.hackaton.booking.api.domain.model.AddOn;
import com.hackaton.booking.api.domain.model.Amenity;
import com.hackaton.booking.api.domain.model.Bathroom;
import com.hackaton.booking.api.domain.model.Booking;
import com.hackaton.booking.api.domain.model.Building;
import com.hackaton.booking.api.domain.model.Furniture;
import com.hackaton.booking.api.domain.model.Location;
import com.hackaton.booking.api.domain.model.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookingMapper {

    Booking ofRequest(BookingRequestDTO requestDTO);

    List<Booking> ofRequest(List<BookingRequestDTO> requestDTO);

    BookingResponseDTO of(Booking booking);

    List<BookingResponseDTO> of(List<Booking> booking);

    AmenityResponseDTO ofAmenity(Amenity amenity);
    List<AmenityResponseDTO> ofAmenity(List<Amenity> amenity);

    BuildingResponseDTO ofBuilding(Building building);

    List<BuildingResponseDTO> ofBuilding(List<Building> building);

    @Mapping(source = "type", target = "type", qualifiedByName = "setBathroomType")
    BathroomResponseDTO ofBathRoom(Bathroom bathroom);

    List<BathroomResponseDTO> ofBathRoom(List<Bathroom> bathroom);

    @Named("setBathroomType")
    default BathroomTypeEnum setBathroomType(String bathroomTypeEnum) {
        return BathroomTypeEnum.valueOf(bathroomTypeEnum);
    }

    FurnitureResponseDTO ofFurniture(Furniture furniture);

    List<FurnitureResponseDTO> ofFurniture(List<Furniture> furniture);

    @Mapping(source = "type", target = "type", qualifiedByName = "setAddOnType")
    AddOnResponseDTO ofAddOn(AddOn addOn);

    List<AddOnResponseDTO> ofAddOn(List<AddOn> addOn);

    @Named("setAddOnType")
    default AddOnTypeEnum setAddOnType(String addOnType) {
        return AddOnTypeEnum.valueOf(addOnType);
    }

    Location ofLocationRequest(BookingFilterRequestDTO requestDTO);

    LocationResponseDTO ofLocation(Location location);

    List<LocationResponseDTO> ofLocation(List<Location> location);

    @Mapping(source = "type", target = "type", qualifiedByName = "getRoomTypeEnum")
    Room ofRoomRequest(BookingFilterRequestDTO requestDTO);

    @Mapping(source = "type", target = "type", qualifiedByName = "setRoomTypeEnum")
    RoomResponseDTO ofRoom(Room room);

    List<RoomResponseDTO> ofRoom(List<Room> room);

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
