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
@Table(name = "location")
public class Location {
    @Id
    @SequenceGenerator(name = "location_seq",
            sequenceName = "location_seq", allocationSize = 1)
    @GeneratedValue(generator = "location_seq")
    @Column(name = "id", unique = true)
    @NotNull(message = "id should not be null")
    private Long id;
    @NotNull(message = "name should not be null")
    @NotEmpty(message = "name should not be null")
    @Column(name = "name")
    private String name;
    @NotNull(message = "street should not be null")
    @NotEmpty(message = "street should not be null")
    @Column(name = "street")
    private String street;
    @NotNull(message = "cep should not be null")
    @NotEmpty(message = "cep should not be null")
    @Column(name = "cep")
    private Long cep;
    @NotNull(message = "city should not be null")
    @NotEmpty(message = "city should not be null")
    @Column(name = "city")
    private String city;
    @NotNull(message = "state should not be null")
    @NotEmpty(message = "state should not be null")
    @Column(name = "state")
    private String state;
    @CreationTimestamp
    @Column(name = "created_at")
    private OffsetDateTime createdAt;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private OffsetDateTime updatedAt;
}
