package com.tech.challenge.parking.meter.api.repository;

import com.tech.challenge.parking.meter.api.domain.model.Parquimetro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParquimetroRepository extends JpaRepository<Parquimetro, Long> {

}
