package com.hackaton.booking.api.domain.mapper;

import com.hackaton.booking.api.domain.dto.request.BookingAddOnRequestDTO;
import com.hackaton.booking.api.domain.dto.response.BookingAddOnResponseDTO;
import com.hackaton.booking.api.domain.model.BookingAddOn;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-15T08:01:26-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class BookingAddOnMapperImpl implements BookingAddOnMapper {

    @Override
    public BookingAddOn ofRequest(BookingAddOnRequestDTO requestDTO) {
        if ( requestDTO == null ) {
            return null;
        }

        BookingAddOn bookingAddOn = new BookingAddOn();

        bookingAddOn.setIdBooking( requestDTO.getIdBooking() );
        bookingAddOn.setIdAddOn( requestDTO.getIdAddOn() );

        return bookingAddOn;
    }

    @Override
    public List<BookingAddOn> ofRequest(List<BookingAddOnRequestDTO> requestDTO) {
        if ( requestDTO == null ) {
            return null;
        }

        List<BookingAddOn> list = new ArrayList<BookingAddOn>( requestDTO.size() );
        for ( BookingAddOnRequestDTO bookingAddOnRequestDTO : requestDTO ) {
            list.add( ofRequest( bookingAddOnRequestDTO ) );
        }

        return list;
    }

    @Override
    public BookingAddOnResponseDTO of(BookingAddOn booking) {
        if ( booking == null ) {
            return null;
        }

        BookingAddOnResponseDTO bookingAddOnResponseDTO = new BookingAddOnResponseDTO();

        bookingAddOnResponseDTO.setId( booking.getId() );
        bookingAddOnResponseDTO.setIdBooking( booking.getIdBooking() );
        bookingAddOnResponseDTO.setIdAddOn( booking.getIdAddOn() );
        bookingAddOnResponseDTO.setCreatedAt( booking.getCreatedAt() );
        bookingAddOnResponseDTO.setUpdatedAt( booking.getUpdatedAt() );

        return bookingAddOnResponseDTO;
    }

    @Override
    public List<BookingAddOnResponseDTO> of(List<BookingAddOn> booking) {
        if ( booking == null ) {
            return null;
        }

        List<BookingAddOnResponseDTO> list = new ArrayList<BookingAddOnResponseDTO>( booking.size() );
        for ( BookingAddOn bookingAddOn : booking ) {
            list.add( of( bookingAddOn ) );
        }

        return list;
    }
}
