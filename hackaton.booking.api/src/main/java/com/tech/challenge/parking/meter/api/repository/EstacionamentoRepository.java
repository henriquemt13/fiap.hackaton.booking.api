package com.tech.challenge.parking.meter.api.repository;

import com.tech.challenge.parking.meter.api.domain.model.Estacionamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EstacionamentoRepository extends JpaRepository<Estacionamento, Long> {

    List<Estacionamento> findByPlaca(String placa);

    List<Estacionamento> findBySaidaIsNull();

    Optional<Estacionamento> findFirstByPlacaOrderByCreatedAtDesc(String placa);


}
