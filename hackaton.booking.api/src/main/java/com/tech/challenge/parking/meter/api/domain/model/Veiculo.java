package com.tech.challenge.parking.meter.api.domain.model;

import com.tech.challenge.parking.meter.api.enums.TipoVeiculoEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "veiculo")
public class Veiculo {

    @Id
    @Column(name = "placa", unique = true)
    @NotNull(message = "placa should not be null")
    private String placa;
    @NotNull(message = "tipoVeiculo should not be null")
    @Column(name = "tipo")
    private TipoVeiculoEnum tipoVeiculo;
    @NotNull(message = "Please enter message")
    @NotBlank(message = "Please enter message")
    @Column(name = "modelo")
    private String modelo;
    @NotNull(message = "Please enter cor")
    @NotBlank(message = "Please enter cor")
    @Column(name = "cor")
    private String cor;
    @NotNull(message = "Please enter ano")
    @Column(name = "ano")
    private String ano;
    @NotNull(message = "Please enter nomeDono")
    @NotBlank(message = "Please enter nomeDono")
    @Column(name = "nome_dono")
    private String nomeDono;
    @NotNull(message = "Please enter documentoDono")
    @NotBlank(message = "Please enter documentoDono")
    @Column(name = "documento_dono")
    private String documentoDono;
    @NotNull(message = "Please enter contatoDono")
    @NotBlank(message = "Please enter contatoDono")
    @Column(name = "contato_dono")
    private String contatoDono;
    @CreationTimestamp
    @Column(name = "created_at")
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
