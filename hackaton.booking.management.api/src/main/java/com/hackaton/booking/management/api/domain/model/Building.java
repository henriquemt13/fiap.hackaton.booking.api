package com.hackaton.booking.management.api.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "building")
public class Building {

    @Id
    @SequenceGenerator(name = "building_seq",
            sequenceName = "building_seq", allocationSize = 1)
    @GeneratedValue(generator = "building_seq")
    @Column(name = "id", unique = true)
    @NotNull(message = "id should not be null")
    private Long id;
    @Column(name = "id_location", unique = true)
    @NotNull(message = "id_location should not be null")
    private Long idLocation;
    @NotNull(message = "name should not be null")
    @NotEmpty(message = "name should not be null")
    @Column(name = "name")
    private String name;
    @CreationTimestamp
    @Column(name = "created_at")
    private OffsetDateTime createdAt;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private OffsetDateTime updatedAt;
}
