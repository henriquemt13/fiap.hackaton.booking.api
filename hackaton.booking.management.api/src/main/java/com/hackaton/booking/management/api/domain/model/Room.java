package com.hackaton.booking.management.api.domain.model;

import com.hackaton.booking.management.api.domain.enums.BathroomTypeEnum;
import com.hackaton.booking.management.api.domain.enums.RoomTypeEnum;
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
@Table(name = "room")
public class Room {

    @Id
    @SequenceGenerator(name = "room_seq",
            sequenceName = "room_seq", allocationSize = 1)
    @GeneratedValue(generator = "room_seq")
    @Column(name = "id", unique = true)
    @NotNull(message = "id should not be null")
    private Long id;
    @Column(name = "id_building", unique = true)
    @NotNull(message = "idBuilding should not be null")
    private Long idBuilding;
    @Column(name = "type", unique = true)
    @NotNull(message = "type should not be null")
    private RoomTypeEnum type;
    @Column(name = "max_capacity", unique = true)
    @NotNull(message = "maxCapacity should not be null")
    private Long maxCapacity;
    @Column(name = "total_beds", unique = true)
    @NotNull(message = "totalBeds should not be null")
    private Long totalBeds;
    @Column(name = "total_rooms", unique = true)
    @NotNull(message = "totalRooms should not be null")
    private Long totalRooms;
    @Column(name = "total_daily_value", unique = true)
    @NotNull(message = "totalDailyValue should not be null")
    private BigDecimal totalDailyValue;
    @Column(name = "bathroom_type", unique = true)
    @NotNull(message = "bathroomType should not be null")
    private BathroomTypeEnum bathroomType;
    @CreationTimestamp
    @Column(name = "created_at")
    private OffsetDateTime createdAt;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private OffsetDateTime updatedAt;
}
