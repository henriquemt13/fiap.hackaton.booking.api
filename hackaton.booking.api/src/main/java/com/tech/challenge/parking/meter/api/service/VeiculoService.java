package com.tech.challenge.parking.meter.api.service;

import com.tech.challenge.parking.meter.api.domain.dto.request.UpdateVeiculoDTO;
import com.tech.challenge.parking.meter.api.domain.dto.request.VeiculoRequestDTO;
import com.tech.challenge.parking.meter.api.domain.dto.response.VeiculoResponseDTO;
import com.tech.challenge.parking.meter.api.domain.mapper.VeiculoMapper;
import com.tech.challenge.parking.meter.api.domain.model.Veiculo;
import com.tech.challenge.parking.meter.api.exceptions.VeiculoAlreadyRegisteredException;
import com.tech.challenge.parking.meter.api.exceptions.VeiculoNotFoundException;
import com.tech.challenge.parking.meter.api.repository.VeiculoRepository;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VeiculoService {

    private VeiculoRepository repository;
    private VeiculoMapper mapper;

    public VeiculoResponseDTO save(VeiculoRequestDTO requestDTO) {
        if (!veiculoExists(requestDTO.getPlaca())) {
            var veiculo = mapper.of(requestDTO);
            veiculo.setCreatedBy("System");
            return mapper.of(repository.save(veiculo));
        }
        throw new VeiculoAlreadyRegisteredException(requestDTO.getPlaca());
    }

    public List<VeiculoResponseDTO> findByFilter(VeiculoRequestDTO requestDTO) {
        ExampleMatcher matcher = getExampleMatcher();
        Example<Veiculo> example = Example.of(mapper.of(requestDTO), matcher);
        return mapper.of(repository.findAll(example));
    }

    private ExampleMatcher getExampleMatcher() {
        return ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();
    }

    public void update(String currentPlaca, UpdateVeiculoDTO updateVeiculoDTO) {
        var veiculo = findByPlaca(currentPlaca);
        if (veiculo.isEmpty()) {
            throw new VeiculoNotFoundException(currentPlaca);
        }
        repository.save(mapper.of(updateVeiculoDTO, veiculo.get()));
    }

    public boolean veiculoExists(String placa) {
        return repository.existsById(placa);
    }

    public Optional<Veiculo> findByPlaca(String placa) {
        return repository.findByPlaca(placa);
    }
}
