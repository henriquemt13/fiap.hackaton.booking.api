package com.hackaton.booking.api.domain.mapper;

import com.hackaton.booking.api.domain.dto.response.ClientResponseDTO;
import com.hackaton.booking.api.domain.dto.request.ClientRequestDTO;
import com.hackaton.booking.api.domain.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClientMapper {

    Client ofRequest(ClientRequestDTO requestDTO);

    List<Client> ofRequest(List<ClientRequestDTO> requestDTO);

    ClientResponseDTO of(Client client);

    List<ClientResponseDTO> of(List<Client> client);
}
