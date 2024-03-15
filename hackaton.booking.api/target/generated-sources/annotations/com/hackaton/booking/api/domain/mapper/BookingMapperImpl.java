package com.hackaton.booking.api.domain.mapper;

import com.hackaton.booking.api.domain.dto.request.BookingFilterRequestDTO;
import com.hackaton.booking.api.domain.dto.request.BookingRequestDTO;
import com.hackaton.booking.api.domain.dto.response.AddOnResponseDTO;
import com.hackaton.booking.api.domain.dto.response.AmenityResponseDTO;
import com.hackaton.booking.api.domain.dto.response.BathroomResponseDTO;
import com.hackaton.booking.api.domain.dto.response.BookingResponseDTO;
import com.hackaton.booking.api.domain.dto.response.BuildingResponseDTO;
import com.hackaton.booking.api.domain.dto.response.FurnitureResponseDTO;
import com.hackaton.booking.api.domain.dto.response.LocationResponseDTO;
import com.hackaton.booking.api.domain.dto.response.RoomResponseDTO;
import com.hackaton.booking.api.domain.model.AddOn;
import com.hackaton.booking.api.domain.model.Amenity;
import com.hackaton.booking.api.domain.model.Bathroom;
import com.hackaton.booking.api.domain.model.Booking;
import com.hackaton.booking.api.domain.model.Building;
import com.hackaton.booking.api.domain.model.Furniture;
import com.hackaton.booking.api.domain.model.Location;
import com.hackaton.booking.api.domain.model.Room;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-15T10:42:55-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class BookingMapperImpl implements BookingMapper {

    @Override
    public Booking ofRequest(BookingRequestDTO requestDTO) {
        if ( requestDTO == null ) {
            return null;
        }

        Booking booking = new Booking();

        booking.setIdRoom( requestDTO.getIdRoom() );
        booking.setIdClient( requestDTO.getIdClient() );
        booking.setStartDate( requestDTO.getStartDate() );
        booking.setEndDate( requestDTO.getEndDate() );

        return booking;
    }

    @Override
    public List<Booking> ofRequest(List<BookingRequestDTO> requestDTO) {
        if ( requestDTO == null ) {
            return null;
        }

        List<Booking> list = new ArrayList<Booking>( requestDTO.size() );
        for ( BookingRequestDTO bookingRequestDTO : requestDTO ) {
            list.add( ofRequest( bookingRequestDTO ) );
        }

        return list;
    }

    @Override
    public BookingResponseDTO of(Booking booking) {
        if ( booking == null ) {
            return null;
        }

        BookingResponseDTO bookingResponseDTO = new BookingResponseDTO();

        bookingResponseDTO.setId( booking.getId() );
        bookingResponseDTO.setIdRoom( booking.getIdRoom() );
        bookingResponseDTO.setIdClient( booking.getIdClient() );
        bookingResponseDTO.setStartDate( booking.getStartDate() );
        bookingResponseDTO.setEndDate( booking.getEndDate() );
        bookingResponseDTO.setTotalValue( booking.getTotalValue() );
        bookingResponseDTO.setCreatedAt( booking.getCreatedAt() );
        bookingResponseDTO.setUpdatedAt( booking.getUpdatedAt() );

        return bookingResponseDTO;
    }

    @Override
    public List<BookingResponseDTO> of(List<Booking> booking) {
        if ( booking == null ) {
            return null;
        }

        List<BookingResponseDTO> list = new ArrayList<BookingResponseDTO>( booking.size() );
        for ( Booking booking1 : booking ) {
            list.add( of( booking1 ) );
        }

        return list;
    }

    @Override
    public AmenityResponseDTO ofAmenity(Amenity amenity) {
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
    public List<AmenityResponseDTO> ofAmenity(List<Amenity> amenity) {
        if ( amenity == null ) {
            return null;
        }

        List<AmenityResponseDTO> list = new ArrayList<AmenityResponseDTO>( amenity.size() );
        for ( Amenity amenity1 : amenity ) {
            list.add( ofAmenity( amenity1 ) );
        }

        return list;
    }

    @Override
    public BuildingResponseDTO ofBuilding(Building building) {
        if ( building == null ) {
            return null;
        }

        BuildingResponseDTO buildingResponseDTO = new BuildingResponseDTO();

        buildingResponseDTO.setId( building.getId() );
        buildingResponseDTO.setIdLocation( building.getIdLocation() );
        buildingResponseDTO.setName( building.getName() );
        buildingResponseDTO.setCreatedAt( building.getCreatedAt() );
        buildingResponseDTO.setUpdatedAt( building.getUpdatedAt() );

        return buildingResponseDTO;
    }

    @Override
    public List<BuildingResponseDTO> ofBuilding(List<Building> building) {
        if ( building == null ) {
            return null;
        }

        List<BuildingResponseDTO> list = new ArrayList<BuildingResponseDTO>( building.size() );
        for ( Building building1 : building ) {
            list.add( ofBuilding( building1 ) );
        }

        return list;
    }

    @Override
    public BathroomResponseDTO ofBathRoom(Bathroom bathroom) {
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
    public List<BathroomResponseDTO> ofBathRoom(List<Bathroom> bathroom) {
        if ( bathroom == null ) {
            return null;
        }

        List<BathroomResponseDTO> list = new ArrayList<BathroomResponseDTO>( bathroom.size() );
        for ( Bathroom bathroom1 : bathroom ) {
            list.add( ofBathRoom( bathroom1 ) );
        }

        return list;
    }

    @Override
    public FurnitureResponseDTO ofFurniture(Furniture furniture) {
        if ( furniture == null ) {
            return null;
        }

        FurnitureResponseDTO furnitureResponseDTO = new FurnitureResponseDTO();

        furnitureResponseDTO.setId( furniture.getId() );
        furnitureResponseDTO.setName( furniture.getName() );
        furnitureResponseDTO.setQuantity( furniture.getQuantity() );
        furnitureResponseDTO.setCreatedAt( furniture.getCreatedAt() );
        furnitureResponseDTO.setUpdatedAt( furniture.getUpdatedAt() );

        return furnitureResponseDTO;
    }

    @Override
    public List<FurnitureResponseDTO> ofFurniture(List<Furniture> furniture) {
        if ( furniture == null ) {
            return null;
        }

        List<FurnitureResponseDTO> list = new ArrayList<FurnitureResponseDTO>( furniture.size() );
        for ( Furniture furniture1 : furniture ) {
            list.add( ofFurniture( furniture1 ) );
        }

        return list;
    }

    @Override
    public AddOnResponseDTO ofAddOn(AddOn addOn) {
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
    public List<AddOnResponseDTO> ofAddOn(List<AddOn> addOn) {
        if ( addOn == null ) {
            return null;
        }

        List<AddOnResponseDTO> list = new ArrayList<AddOnResponseDTO>( addOn.size() );
        for ( AddOn addOn1 : addOn ) {
            list.add( ofAddOn( addOn1 ) );
        }

        return list;
    }

    @Override
    public Location ofLocationRequest(BookingFilterRequestDTO requestDTO) {
        if ( requestDTO == null ) {
            return null;
        }

        Location location = new Location();

        location.setCity( requestDTO.getCity() );
        location.setState( requestDTO.getState() );

        return location;
    }

    @Override
    public LocationResponseDTO ofLocation(Location location) {
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
    public List<LocationResponseDTO> ofLocation(List<Location> location) {
        if ( location == null ) {
            return null;
        }

        List<LocationResponseDTO> list = new ArrayList<LocationResponseDTO>( location.size() );
        for ( Location location1 : location ) {
            list.add( ofLocation( location1 ) );
        }

        return list;
    }

    @Override
    public Room ofRoomRequest(BookingFilterRequestDTO requestDTO) {
        if ( requestDTO == null ) {
            return null;
        }

        Room room = new Room();

        room.setType( getRoomTypeEnum( requestDTO.getType() ) );
        room.setMaxCapacity( requestDTO.getMaxCapacity() );
        room.setTotalBeds( requestDTO.getTotalBeds() );
        if ( requestDTO.getTotalDailyValue() != null ) {
            room.setTotalDailyValue( BigDecimal.valueOf( requestDTO.getTotalDailyValue() ) );
        }

        return room;
    }

    @Override
    public RoomResponseDTO ofRoom(Room room) {
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
    public List<RoomResponseDTO> ofRoom(List<Room> room) {
        if ( room == null ) {
            return null;
        }

        List<RoomResponseDTO> list = new ArrayList<RoomResponseDTO>( room.size() );
        for ( Room room1 : room ) {
            list.add( ofRoom( room1 ) );
        }

        return list;
    }
}
