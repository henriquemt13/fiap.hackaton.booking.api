package com.hackaton.booking.api.usecase;

import com.hackaton.booking.api.domain.model.AddOn;
import com.hackaton.booking.api.domain.model.BookingAddOn;
import com.hackaton.booking.api.exceptions.NotFoundException;
import com.hackaton.booking.api.repository.BookingAddOnRepository;
import com.hackaton.booking.api.service.AddOnService;
import com.hackaton.booking.api.service.BookingAddOnService;
import com.hackaton.booking.api.service.BookingService;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.lang.String.format;

@Component
public class BookingAddOnUseCase extends BookingAddOnService {

    private final AddOnService addOnService;
    private final BookingService bookingService;

    public BookingAddOnUseCase(BookingAddOnRepository repository, AddOnService addOnService,
                               BookingService bookingService) {
        super(repository);
        this.addOnService = addOnService;
        this.bookingService = bookingService;
    }

    public List<AddOn> findAllAddOns() {
        return addOnService.findAll();
    }

    public BookingAddOn saveAddOn(BookingAddOn bookingAddOn) {
        validateIdBooking(bookingAddOn.getIdBooking());
        validateIdAddOn(bookingAddOn.getIdAddOn());
        return super.save(bookingAddOn);
    }

    private void validateIdBooking(Long idBooking) {
        if (bookingService.findById(idBooking).isEmpty()) {
            throw new NotFoundException(format("Booking ID %d not found", idBooking));
        }
    }

    private void validateIdAddOn(Long idAddOn) {
        if (addOnService.findById(idAddOn).isEmpty()) {
            throw new NotFoundException(format("AddOn ID %d not found", idAddOn));
        }
    }
}
