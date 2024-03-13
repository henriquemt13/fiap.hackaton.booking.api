package com.tech.challenge.parking.meter.api.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
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
@Table(name = "parquimetro")
public class Parquimetro {

    @Id
    @SequenceGenerator(name = "parquimetro_seq",
          sequenceName = "parquimetro_seq", allocationSize = 1)
    @GeneratedValue(generator = "parquimetro_seq")
    @Column(name = "id", unique = true)
    @NotNull(message = "id should not be null")
    private Long id;
    @Column(name = "total_vagas_cidade")
    private Integer totalVagasCidade;
    @NotNull(message = "precoInicial should not be null")
    @Column(name = "preco_inicial")
    private BigDecimal precoInicial;
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
