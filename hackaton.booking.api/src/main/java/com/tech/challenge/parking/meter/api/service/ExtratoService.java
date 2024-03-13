package com.tech.challenge.parking.meter.api.service;

import com.tech.challenge.parking.meter.api.domain.dto.response.ExtratoResponseDTO;
import com.tech.challenge.parking.meter.api.domain.dto.response.ParquimetroResponseDTO;
import com.tech.challenge.parking.meter.api.domain.mapper.ExtratoMapper;
import com.tech.challenge.parking.meter.api.domain.model.Estacionamento;
import com.tech.challenge.parking.meter.api.domain.model.Extrato;
import com.tech.challenge.parking.meter.api.enums.TempoContratadoEnum;
import com.tech.challenge.parking.meter.api.exceptions.ExtratoNotFoundException;
import com.tech.challenge.parking.meter.api.exceptions.InvalidAlocacaoException;
import com.tech.challenge.parking.meter.api.repository.ExtratoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class ExtratoService {

    private final ParquimetroService parquimetroService;
    private final EstacionamentoService estacionamentoService;
    private final ExtratoRepository repository;
    private final ExtratoMapper mapper;

    public List<ExtratoResponseDTO> getExtratos(String placa) {
        List<Extrato> extratos = repository.findByPlaca(placa);
        List<ExtratoResponseDTO> responses = new ArrayList<>();
        for (Extrato extrato : extratos) {
            responses.add(mapper.of(extrato.getValorPago(), getAlocacaoById(extrato.getEstacionamentoId())));
        }
        return responses;

    }

    public ExtratoResponseDTO getLastExtrato(String placa) {
        Extrato extrato = getLastExtratoByPlaca(placa);
        return mapper.of(extrato.getValorPago(), getAlocacaoById(extrato.getEstacionamentoId()));
    }

    public void saveNewExtrato(String placa) {
        var parquimetroInfos = parquimetroService.findInfos().get(0);
        var alocacao = estacionamentoService.findLastAlocacao(placa);
        if (alocacao.isEmpty()) {
            throw new InvalidAlocacaoException(placa);
        }
        Extrato extrato = mapper.of(alocacao.get(), placa);
        extrato.setCreatedBy("System");
        extrato.setValorPago(getValorPago(extrato.getTempoContratado(), parquimetroInfos));
        repository.save(extrato);
    }

    private BigDecimal getValorPago(TempoContratadoEnum tempoContratado, ParquimetroResponseDTO parquimetro) {
        if (tempoContratado.ordinal() == 0) {
            return parquimetro.getPrecoInicial();
        }
        return parquimetro.getPrecoInicial()
                .multiply(BigDecimal.valueOf(tempoContratado.ordinal())
                        .multiply(BigDecimal.valueOf(2)));

    }

    public Estacionamento getAlocacaoById(Long id) {
        Optional<Estacionamento> alocacao = estacionamentoService.getAlocacaoById(id);
        if (alocacao.isPresent()) {
            return alocacao.get();
        }
        throw new InvalidAlocacaoException(id);
    }

    private Extrato getLastExtratoByPlaca(String placa) {
        Optional<Extrato> extratoAlocacao = repository.findFirstByPlacaOrderByCreatedAtDesc(placa);
        if (extratoAlocacao.isPresent()) {
            return extratoAlocacao.get();
        }
        throw new ExtratoNotFoundException(placa);
    }
}
