package com.hackaton.booking.api.domain.model;

import jakarta.persistence.*;
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
@Table(name = "booking_add_on")
public class BookingAddOn {

    @Id
    @SequenceGenerator(name = "add_on_seq",
            sequenceName = "add_on_seq", allocationSize = 1)
    @GeneratedValue(generator = "add_on_seq")
    @Column(name = "id", unique = true)
    @NotNull(message = "id should not be null")
    private Long id;
    @Column(name = "id_booking")
    @NotNull(message = "idBooking should not be null")
    private Long idBooking;
    @Column(name = "id_add_on")
    @NotNull(message = "idAddOn should not be null")
    private Long idAddOn;
    @CreationTimestamp
    @Column(name = "created_at")
    private OffsetDateTime createdAt;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private OffsetDateTime updatedAt;
}
