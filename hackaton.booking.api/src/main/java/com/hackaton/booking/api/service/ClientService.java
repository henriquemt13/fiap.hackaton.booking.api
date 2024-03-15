package com.hackaton.booking.api.service;

import com.hackaton.booking.api.exceptions.BadRequestException;
import com.hackaton.booking.api.repository.ClientRepository;
import com.hackaton.booking.api.domain.model.Client;
import com.hackaton.booking.api.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
@AllArgsConstructor
@Transactional
public class ClientService {

    private final ClientRepository repository;

    private final String DEFAULT_NATIONALITY = "Brasil";

    public Client save(Client client) {
        if (findByEmail(client.getEmail()).isPresent()) {
            throw new BadRequestException("Email Already Registered");
        }
        if (client.getNationality().equals(DEFAULT_NATIONALITY) && client.getPassport() == null) {
            throw new BadRequestException("Nationality is Mandatory for Foreigners");
        }
        return repository.save(client);
    }

    public Client update(Long id, Client updateClient) {
        if (updateClient.getNationality().equals(DEFAULT_NATIONALITY) && updateClient.getPassport() == null) {
            throw new BadRequestException("Nationality is Mandatory for Foreigners");
        }
        var currentClient = validateId(id);
        updateClient.setId(currentClient.getId());
        updateClient.setCreatedAt(currentClient.getCreatedAt());
        updateClient.setUpdatedAt(OffsetDateTime.now());
        return repository.save(updateClient);
    }

    public Optional<Client> findById(Long id) {
        return repository.findById(id);
    }

    public Optional<Client> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public List<Client> findAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.delete(validateId(id));
    }

    private Client validateId(Long id) {
        var optClient = findById(id);
        if (optClient.isEmpty()) {
            throw new NotFoundException(format("Client ID %d not found", id));
        }
        return optClient.get();
    }
}
