package com.hackaton.booking.api.domain.mapper;

import com.hackaton.booking.api.domain.dto.response.BookingResponseDTO;
import com.hackaton.booking.api.domain.dto.request.BookingRequestDTO;
import com.hackaton.booking.api.domain.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookingMapper {

    Booking ofRequest(BookingRequestDTO requestDTO);

    List<Booking> ofRequest(List<BookingRequestDTO> requestDTO);

    BookingResponseDTO of(Booking booking);

    List<BookingResponseDTO> of(List<Booking> booking);
}
