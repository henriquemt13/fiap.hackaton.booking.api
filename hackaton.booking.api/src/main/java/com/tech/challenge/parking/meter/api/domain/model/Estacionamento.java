package com.tech.challenge.parking.meter.api.domain.model;

import com.tech.challenge.parking.meter.api.enums.TempoContratadoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "estacionamento")
public class Estacionamento {

    @Id
    @SequenceGenerator(name = "estacionamento_seq",
          sequenceName = "estacionamento_seq", allocationSize = 1)
    @GeneratedValue(generator = "estacionamento_seq")
    @Column(name = "id", unique = true)
    @NotNull(message = "id should not be null")
    private Long id;
    @NotNull(message = "placa should not be null")
    @NotEmpty(message = "placa should not be empty")
    @Column(name = "placa")
    private String placa;
    @Column(name = "entrada")
    private LocalDateTime entrada;
    @Column(name = "tempo_contratado")
    private TempoContratadoEnum tempoContratado;
    @Column(name = "saida")
    private LocalDateTime saida;
    @Column(name = "endereco_estacionamento")
    private String enderecoEstacionamento;
    @Column(name = "created_at")
    @CreationTimestamp
    private OffsetDateTime createdAt;
    @NotNull(message = "Please enter createdBy")
    @NotBlank(message = "Please enter createdBy")
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private OffsetDateTime updatedAt;
    @Column(name = "updated_by")
    private String updatedBy;

}
