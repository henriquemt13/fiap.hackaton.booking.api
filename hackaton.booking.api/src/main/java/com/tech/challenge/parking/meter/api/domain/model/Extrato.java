package com.tech.challenge.parking.meter.api.domain.model;

import com.tech.challenge.parking.meter.api.enums.TempoContratadoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "extrato")
public class Extrato {

    @Id
    @SequenceGenerator(name = "extrato_seq",
            sequenceName = "extrato_seq", allocationSize = 1)
    @GeneratedValue(generator = "extrato_seq")
    @Column(name = "id", unique = true)
    @NotNull(message = "id should not be null")
    private Long id;
    @Column(name = "estacionamento_id", unique = true)
    @NotNull(message = "estacionamento_id should not be null")
    private Long estacionamentoId;
    @NotNull(message = "placa should not be null")
    @NotEmpty(message = "placa should not be empty")
    @Column(name = "placa")
    private String placa;
    @Column(name = "tempo_contratado")
    private TempoContratadoEnum tempoContratado;
    @Column(name = "valor_pago")
    private BigDecimal valorPago;
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
