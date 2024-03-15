package com.hackaton.booking.api.service;

import com.hackaton.booking.api.repository.BookingAddOnRepository;
import com.hackaton.booking.api.domain.model.BookingAddOn;
import com.hackaton.booking.api.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
@AllArgsConstructor
@Transactional
public class BookingAddOnService {

    private final BookingAddOnRepository repository;

    public BookingAddOn save(BookingAddOn bookingAddOn) {
        return repository.save(bookingAddOn);
    }

    public BookingAddOn update(Long id, BookingAddOn updateBooking) {
        var currentBooking = validateId(id);
        updateBooking.setId(currentBooking.getId());
        updateBooking.setCreatedAt(currentBooking.getCreatedAt());
        updateBooking.setUpdatedAt(OffsetDateTime.now());
        return repository.save(updateBooking);
    }

    public Optional<BookingAddOn> findById(Long id) {
        return repository.findById(id);
    }

    public List<BookingAddOn> findAll() {
        return repository.findAll();
    }

    public List<BookingAddOn> findByIdBooking(Long idBooking) {
        return repository.findByIdBooking(idBooking);
    }

    public void delete(Long id) {
        repository.delete(validateId(id));
    }

    public void deleteByIdBooking(Long idBooking) {
        repository.deleteByIdBooking(idBooking);
    }

    private BookingAddOn validateId(Long id) {
        var optBookingAddOn = findById(id);
        if (optBookingAddOn.isEmpty()) {
            throw new NotFoundException(format("BookingAddOn ID %d not found", id));
        }
        return optBookingAddOn.get();
    }
}
