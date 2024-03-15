package com.hackaton.booking.api.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @SequenceGenerator(name = "add_on_seq",
            sequenceName = "add_on_seq", allocationSize = 1)
    @GeneratedValue(generator = "add_on_seq")
    @Column(name = "id", unique = true)
    @NotNull(message = "id should not be null")
    private Long id;
    @Column(name = "id_room")
    @NotNull(message = "idRoom should not be null")
    private Long idRoom;
    @Column(name = "id_client")
    @NotNull(message = "idClient should not be null")
    private Long idClient;
    @Column(name = "start_date")
    @NotNull(message = "startDate should not be null")
    private LocalDate startDate;
    @NotNull(message = "endDate should not be null")
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "total_value")
    private BigDecimal totalValue;
    @CreationTimestamp
    @Column(name = "created_at")
    private OffsetDateTime createdAt;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private OffsetDateTime updatedAt;
}
