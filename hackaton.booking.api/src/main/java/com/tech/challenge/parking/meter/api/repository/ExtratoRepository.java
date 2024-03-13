package com.tech.challenge.parking.meter.api.repository;

import com.tech.challenge.parking.meter.api.domain.model.Extrato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ExtratoRepository extends JpaRepository<Extrato, Long> {

    List<Extrato> findByPlaca(String placa);

    Optional<Extrato> findFirstByPlacaOrderByCreatedAtDesc(String placa);
}
