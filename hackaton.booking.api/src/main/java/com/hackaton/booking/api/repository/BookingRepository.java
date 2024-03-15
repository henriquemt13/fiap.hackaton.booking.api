package com.hackaton.booking.api.repository;

import com.hackaton.booking.api.domain.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("""
    select b from Booking b
    where b.idRoom = :id
    AND (b.startDate <= :endDate AND b.endDate >= :startDate)
    """)
    List<Booking> findByFilter(Long id, LocalDate startDate, LocalDate endDate);
}
