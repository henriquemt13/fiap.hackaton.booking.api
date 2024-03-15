package com.hackaton.booking.api.domain.mapper;

import com.hackaton.booking.api.domain.dto.request.BookingAddOnRequestDTO;
import com.hackaton.booking.api.domain.dto.response.BookingAddOnResponseDTO;
import com.hackaton.booking.api.domain.model.BookingAddOn;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookingAddOnMapper {

    BookingAddOn ofRequest(BookingAddOnRequestDTO requestDTO);

    List<BookingAddOn> ofRequest(List<BookingAddOnRequestDTO> requestDTO);

    BookingAddOnResponseDTO of(BookingAddOn booking);

    List<BookingAddOnResponseDTO> of(List<BookingAddOn> booking);
}
