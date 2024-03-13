package com.tech.challenge.parking.meter.api.service;

import com.tech.challenge.parking.meter.api.domain.dto.request.UpdateParquimetroDTO;
import com.tech.challenge.parking.meter.api.domain.dto.response.ParquimetroResponseDTO;
import com.tech.challenge.parking.meter.api.domain.mapper.ParquimetroMapper;
import com.tech.challenge.parking.meter.api.repository.ParquimetroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ParquimetroService {

    private final ParquimetroRepository repository;
    private final ParquimetroMapper mapper;

    public void update(UpdateParquimetroDTO parquimetroDTO) {
        var parquimetro = mapper.of(parquimetroDTO, repository.findAll().get(0));
        parquimetro.setCreatedBy("System");
        repository.save(parquimetro);
    }

    public List<ParquimetroResponseDTO> findInfos() {
        return mapper.of(repository.findAll());
    }

    public Integer getTotalVagas() {
        return findInfos().get(0).getTotalVagasCidade();
    }
}
