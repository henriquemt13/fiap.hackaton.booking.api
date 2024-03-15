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
@Table(name = "bathroom")
public class Bathroom {

    @Id
    @SequenceGenerator(name = "bathroom_seq",
            sequenceName = "bathroom_seq", allocationSize = 1)
    @GeneratedValue(generator = "bathroom_seq")
    @Column(name = "id", unique = true)
    @NotNull(message = "id should not be null")
    private Long id;
    @NotNull(message = "type should not be null")
    @NotEmpty(message = "type should not be null")
    private String type;
    @NotNull(message = "description should not be null")
    @NotEmpty(message = "description should not be null")
    @Column(name = "description")
    private String description;
    @CreationTimestamp
    @Column(name = "created_at")
    private OffsetDateTime createdAt;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private OffsetDateTime updatedAt;
}
