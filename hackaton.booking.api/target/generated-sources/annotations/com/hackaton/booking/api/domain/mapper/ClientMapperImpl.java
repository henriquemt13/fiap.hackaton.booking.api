package com.hackaton.booking.api.domain.mapper;

import com.hackaton.booking.api.domain.dto.request.ClientRequestDTO;
import com.hackaton.booking.api.domain.dto.response.ClientResponseDTO;
import com.hackaton.booking.api.domain.model.Client;
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
public class ClientMapperImpl implements ClientMapper {

    @Override
    public Client ofRequest(ClientRequestDTO requestDTO) {
        if ( requestDTO == null ) {
            return null;
        }

        Client client = new Client();

        client.setCpf( requestDTO.getCpf() );
        client.setPassport( requestDTO.getPassport() );
        client.setFullName( requestDTO.getFullName() );
        client.setBirthdayDate( requestDTO.getBirthdayDate() );
        client.setNationality( requestDTO.getNationality() );
        client.setHomeCountryAddress( requestDTO.getHomeCountryAddress() );
        client.setPhone( requestDTO.getPhone() );
        client.setEmail( requestDTO.getEmail() );

        return client;
    }

    @Override
    public List<Client> ofRequest(List<ClientRequestDTO> requestDTO) {
        if ( requestDTO == null ) {
            return null;
        }

        List<Client> list = new ArrayList<Client>( requestDTO.size() );
        for ( ClientRequestDTO clientRequestDTO : requestDTO ) {
            list.add( ofRequest( clientRequestDTO ) );
        }

        return list;
    }

    @Override
    public ClientResponseDTO of(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientResponseDTO clientResponseDTO = new ClientResponseDTO();

        clientResponseDTO.setId( client.getId() );
        clientResponseDTO.setCpf( client.getCpf() );
        clientResponseDTO.setPassport( client.getPassport() );
        clientResponseDTO.setFullName( client.getFullName() );
        clientResponseDTO.setBirthdayDate( client.getBirthdayDate() );
        clientResponseDTO.setNationality( client.getNationality() );
        clientResponseDTO.setHomeCountryAddress( client.getHomeCountryAddress() );
        clientResponseDTO.setPhone( client.getPhone() );
        clientResponseDTO.setEmail( client.getEmail() );
        clientResponseDTO.setCreatedAt( client.getCreatedAt() );
        clientResponseDTO.setUpdatedAt( client.getUpdatedAt() );

        return clientResponseDTO;
    }

    @Override
    public List<ClientResponseDTO> of(List<Client> client) {
        if ( client == null ) {
            return null;
        }

        List<ClientResponseDTO> list = new ArrayList<ClientResponseDTO>( client.size() );
        for ( Client client1 : client ) {
            list.add( of( client1 ) );
        }

        return list;
    }
}
