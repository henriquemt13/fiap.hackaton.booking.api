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
@Table(name = "amenity")
public class Amenity {

    @Id
    @SequenceGenerator(name = "amenity_seq",
            sequenceName = "amenity_seq", allocationSize = 1)
    @GeneratedValue(generator = "amenity_seq")
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
    @NotNull(message = "quantity should not be null")
    @NotEmpty(message = "quantity should not be null")
    @Column(name = "quantity")
    private Long quantity;
    @CreationTimestamp
    @Column(name = "created_at")
    private OffsetDateTime createdAt;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private OffsetDateTime updatedAt;
}
