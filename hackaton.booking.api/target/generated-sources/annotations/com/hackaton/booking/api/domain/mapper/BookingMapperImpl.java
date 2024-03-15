package com.hackaton.booking.api.domain.mapper;

import com.hackaton.booking.api.domain.dto.request.BookingRequestDTO;
import com.hackaton.booking.api.domain.dto.response.BookingResponseDTO;
import com.hackaton.booking.api.domain.model.Booking;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-14T21:53:20-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
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
}
