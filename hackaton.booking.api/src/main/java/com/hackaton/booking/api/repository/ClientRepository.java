package com.hackaton.booking.api.repository;

import com.hackaton.booking.api.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
