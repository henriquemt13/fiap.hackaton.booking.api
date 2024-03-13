package com.hackaton.booking.management.api.domain.model;

import com.hackaton.booking.management.api.domain.enums.AddOnTypeEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "add_on")
public class AddOn {

    @Id
    @SequenceGenerator(name = "add_on_seq",
            sequenceName = "add_on_seq", allocationSize = 1)
    @GeneratedValue(generator = "add_on_seq")
    @Column(name = "id", unique = true)
    @NotNull(message = "id should not be null")
    private Long id;
    @Column(name = "type", unique = true)
    @NotNull(message = "type should not be null")
    private AddOnTypeEnum type;
    @NotNull(message = "description should not be null")
    @NotEmpty(message = "description should not be null")
    @Column(name = "description")
    private String description;
    @Column(name = "total_value", unique = true)
    @NotNull(message = "totalValue should not be null")
    private BigDecimal totalValue;
    @CreationTimestamp
    @Column(name = "created_at")
    private OffsetDateTime createdAt;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private OffsetDateTime updatedAt;
}
