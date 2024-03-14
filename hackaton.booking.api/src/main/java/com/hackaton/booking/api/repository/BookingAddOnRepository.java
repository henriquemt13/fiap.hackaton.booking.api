package com.hackaton.booking.api.repository;

import com.hackaton.booking.api.domain.model.BookingAddOn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingAddOnRepository extends JpaRepository<BookingAddOn, Long> {

    List<BookingAddOn> findByIdBooking(Long idBooking);

    void deleteByIdBooking(Long idBooking);
}
