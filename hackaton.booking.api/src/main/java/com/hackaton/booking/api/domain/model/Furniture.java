package com.hackaton.booking.api.domain.model;

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
@Table(name = "furniture")
public class Furniture {

    @Id
    @SequenceGenerator(name = "furniture_seq",
            sequenceName = "furniture_seq", allocationSize = 1)
    @GeneratedValue(generator = "furniture_seq")
    @Column(name = "id", unique = true)
    @NotNull(message = "id should not be null")
    private Long id;
    @Column(name = "id_room", unique = true)
    @NotNull(message = "idRoom should not be null")
    private Long idRoom;
    @NotNull(message = "name should not be null")
    @NotEmpty(message = "name should not be null")
    @Column(name = "name")
    private String name;
    @NotNull(message = "quantity should not be null")
    @Column(name = "quantity")
    private Long quantity;
    @CreationTimestamp
    @Column(name = "created_at")
    private OffsetDateTime createdAt;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private OffsetDateTime updatedAt;
}
