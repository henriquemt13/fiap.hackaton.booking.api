package com.tech.challenge.parking.meter.api.service;

import com.tech.challenge.parking.meter.api.domain.dto.request.EntradaEstacionamentoRequestDTO;
import com.tech.challenge.parking.meter.api.domain.dto.response.EstacionamentoInfosResponseDTO;
import com.tech.challenge.parking.meter.api.domain.model.Estacionamento;
import com.tech.challenge.parking.meter.api.domain.model.Veiculo;
import com.tech.challenge.parking.meter.api.enums.TipoVeiculoEnum;
import com.tech.challenge.parking.meter.api.exceptions.InvalidAlocacaoException;
import com.tech.challenge.parking.meter.api.exceptions.VeiculoNotFoundException;
import com.tech.challenge.parking.meter.api.repository.EstacionamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EstacionamentoService {

    private final EstacionamentoRepository repository;
    private final ParquimetroService parquimetroService;
    private final VeiculoService veiculoService;
    private final QueueExtratoService queueExtratoService;

    public void createEntrada(String placa, EntradaEstacionamentoRequestDTO requestDTO) {
        if (!isEntradaValid(placa)) {
            throw new InvalidAlocacaoException(placa);
        }
        veiculoService.findByPlaca(placa).ifPresent(veiculo -> repository
                .save(buildEstacionamentoEntrada(placa, requestDTO)));
        queueExtratoService.saveNewExtrato(placa);
    }

    private boolean isEntradaValid(String placa) {
        var lastAlocacaoPlaca = findLastAlocacao(placa);
        return isNotFull()
                && (lastAlocacaoPlaca.isEmpty() || lastAlocacaoPlaca.get().getSaida() != null);
    }

    private boolean isNotFull() {
        return repository.findBySaidaIsNull().size() < parquimetroService.findInfos().get(0).getTotalVagasCidade();
    }

    private Estacionamento buildEstacionamentoEntrada(String placa, EntradaEstacionamentoRequestDTO requestDTO) {
        Estacionamento estacionamento = new Estacionamento();
        estacionamento.setPlaca(placa);
        estacionamento.setTempoContratado(requestDTO.getTempoContratado());
        estacionamento.setEnderecoEstacionamento(requestDTO.getEnderecoEstacionamento());
        estacionamento.setCreatedBy("System");
        estacionamento.setEntrada(LocalDateTime.now());
        return estacionamento;
    }

    public void createSaida(String placa) {
        var saida = validateSaida(placa);
        saida.setUpdatedBy("System");
        saida.setSaida(LocalDateTime.now());
        repository.save(saida);
    }

    private Estacionamento validateSaida(String placa) {
        veiculoService.veiculoExists(placa);
        var alocacao = findLastAlocacao(placa);
        if (alocacao.isPresent() && isSaidaValid(alocacao.get())) {
            return alocacao.get();
        }
        throw new InvalidAlocacaoException(placa);
    }

    private boolean isSaidaValid(Estacionamento estacionamento) {
        return estacionamento.getSaida() == null;
    }

    public Optional<Estacionamento> findLastAlocacao(String placa) {
        return repository.findFirstByPlacaOrderByCreatedAtDesc(placa);
    }

    public EstacionamentoInfosResponseDTO getAlocacaoInfos() {
        var alocacoes = repository.findBySaidaIsNull();
        var veiculos = getVeiculos(alocacoes);
        return EstacionamentoInfosResponseDTO.builder()
                .vagasAlocadas(alocacoes.size())
                .vagasDisponiveis(parquimetroService.getTotalVagas() - alocacoes.size())
                .numeroCarroAlocados(countCarros(veiculos))
                .numeroMotosAlocadas(countMotos(veiculos))
                .build();
    }

    private List<Veiculo> getVeiculos(List<Estacionamento> alocacoes) {
        List<Veiculo> veiculos = new ArrayList<>();
        for (Estacionamento estacionamento : alocacoes) {
            veiculos.add(validateVeiculo(estacionamento.getPlaca()));
        }
        return veiculos;
    }

    private Veiculo validateVeiculo(String placa) {
        var veiculo = veiculoService.findByPlaca(placa);
        if (veiculo.isPresent()) {
            return veiculo.get();
        }
        throw new VeiculoNotFoundException(placa);
    }

    private Integer countCarros(List<Veiculo> veiculos) {
        return veiculos.stream().filter(veiculo -> veiculo.getTipoVeiculo() == TipoVeiculoEnum.CARRO).toList().size();
    }

    private Integer countMotos(List<Veiculo> veiculos) {
        return veiculos.stream().filter(veiculo -> veiculo.getTipoVeiculo() == TipoVeiculoEnum.MOTO).toList().size();
    }

    public Optional<Estacionamento> getAlocacaoById(Long id) {
        return repository.findById(id);
    }
}
