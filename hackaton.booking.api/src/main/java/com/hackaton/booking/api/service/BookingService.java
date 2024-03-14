package com.hackaton.booking.api.service;

import com.hackaton.booking.api.repository.BookingRepository;
import com.hackaton.booking.api.domain.model.Booking;
import com.hackaton.booking.api.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
@AllArgsConstructor
@Transactional
public class BookingService {

    private final BookingRepository repository;

    public Booking save(Booking Booking) {
        return repository.save(Booking);
    }

    public Booking update(Long id, Booking updateBooking) {
        var currentBooking = validateId(id);
        updateBooking.setId(currentBooking.getId());
        updateBooking.setCreatedAt(currentBooking.getCreatedAt());
        updateBooking.setUpdatedAt(OffsetDateTime.now());
        return repository.save(updateBooking);
    }

    public Optional<Booking> findById(Long id) {
        return repository.findById(id);
    }

    public List<Booking> findByFilter(Long id, LocalDate startDate, LocalDate endDate) {
        return repository.findByFilter(id, startDate, endDate);
    }

    public List<Booking> findAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.delete(validateId(id));
    }

    private Booking validateId(Long id) {
        var optBooking = findById(id);
        if (optBooking.isEmpty()) {
            throw new NotFoundException(format("Booking ID %d not found", id));
        }
        return optBooking.get();
    }
}
