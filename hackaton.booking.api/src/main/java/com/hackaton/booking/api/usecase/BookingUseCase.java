package com.hackaton.booking.api.usecase;

import com.hackaton.booking.api.domain.dto.response.LocationResponseDTO;
import com.hackaton.booking.api.domain.model.*;
import com.hackaton.booking.api.exceptions.BadRequestException;
import com.hackaton.booking.api.exceptions.NotFoundException;
import com.hackaton.booking.api.repository.BookingRepository;
import com.hackaton.booking.api.service.BookingService;
import com.hackaton.booking.api.service.ClientService;
import com.hackaton.booking.api.service.MailService;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static java.lang.String.format;

@Component
public class BookingUseCase extends BookingService {

    private final LocationUseCase locationUseCase;
    private final BuildingUseCase buildingUseCase;
    private final RoomUseCase roomUseCase;
    private final ClientService clientService;
    private final MailService mailService;

    private final BookingAddOnUseCase bookingAddOnUseCase;

    public BookingUseCase(BookingRepository repository, LocationUseCase locationUseCase,
                          BuildingUseCase buildingUseCase, RoomUseCase roomUseCase, ClientService clientService,
                          MailService mailService, BookingAddOnUseCase bookingAddOnUseCase) {
        super(repository);
        this.locationUseCase = locationUseCase;
        this.buildingUseCase = buildingUseCase;
        this.roomUseCase = roomUseCase;
        this.clientService = clientService;
        this.mailService = mailService;
        this.bookingAddOnUseCase = bookingAddOnUseCase;
    }

    public Booking book(Booking booking) {
        validateIdClient(booking.getIdClient());
        if (isRoomAvailable(booking)) {
            return super.save(booking);
        }
        throw new BadRequestException(
              format("Room %d is overbooked for the requested date", booking.getIdRoom()));
    }

    private void validateIdClient(Long idClient) {
        if (clientService.findById(idClient).isEmpty()) {
            throw new NotFoundException(format("Client ID %d not found", idClient));
        }
    }

    private boolean isRoomAvailable(Booking booking) {
        var room = findRoomById(booking.getIdRoom());
        var bookedRooms = super.findByFilter(booking.getIdRoom(), booking.getStartDate(),
              booking.getEndDate());
        return bookedRooms == null || (room.getTotalRooms() > bookedRooms.size());
    }

    public List<Location> findLocationsByFilter(Location filter) {
        return locationUseCase.findAll(filter);
    }

    public List<Building> findBuildingsByIdLocation(Long idLocation) {
        return buildingUseCase.findByIdLocation(idLocation);
    }

    public List<Room> findRoomsByFilter(Room filter, LocalDate startDate, LocalDate endDate) {
        var rooms = roomUseCase.findAll(filter);

        for (Room room : rooms) {
            var alreadyBookedRooms = super.findByFilter(room.getId(), startDate, endDate);
            room.setTotalRooms(room.getTotalRooms() - alreadyBookedRooms.size());
        }
        rooms.removeIf(room -> room.getTotalRooms() < 1);
        return rooms;
    }

    public List<Furniture> findFurnitureByIdRoom(Long idRoom) {
        return roomUseCase.findFurnitureByIdRoom(idRoom);
    }

    public Bathroom findBathroomByType(String bathroomType) {
        return roomUseCase.findBathroomByType(bathroomType);
    }

    public List<Amenity> findAmenitiesByIdLocation(Long idLocation) {
        return locationUseCase.findAmenities(idLocation);
    }

    public List<BookingAddOn> findAddOnsByIdBooking(Long idBooking) {
        return bookingAddOnUseCase.findByIdBooking(idBooking);
    }

    public Booking finishBooking(Long id, LocationResponseDTO responseDTO) throws MessagingException {
        var booking = findBookingById(id);
        booking.setTotalValue(getTotalBookingValue(booking));
        responseDTO.getBooking().setTotalValue(booking.getTotalValue());
        mailService.sendMail(responseDTO, booking.getIdClient());
        return super.save(booking);
    }

    private BigDecimal getTotalBookingValue(Booking booking) {
        var selectedAddOns = bookingAddOnUseCase.findAddOnsByIdBookingAddOn(booking.getId());
        var totalAddOnsValue = selectedAddOns.stream().map(AddOn::getTotalValue).reduce(
              BigDecimal::add);
        var selectedRoom = findRoomById(booking.getIdRoom());
        return selectedRoom.getTotalDailyValue().multiply(BigDecimal.valueOf(
                    ChronoUnit.DAYS.between(booking.getStartDate(), booking.getEndDate())))
              .add(totalAddOnsValue.orElse(BigDecimal.ZERO));
    }

    @Override
    public void delete(Long id) {
        bookingAddOnUseCase.deleteByIdBooking(id);
        super.delete(id);
    }

    public Room findRoomById(Long id) {
        var optRoom = roomUseCase.findById(id);
        if (optRoom.isEmpty()) {
            throw new NotFoundException(format("Room Id %d not found", id));
        }
        return optRoom.get();
    }

    public Booking findBookingById(Long id) {
        var optBooking = super.findById(id);
        if (optBooking.isEmpty()) {
            throw new NotFoundException(format("Booking ID %d not found", id));
        }
        return optBooking.get();
    }

    public Location findLocationById(Long id) {
        var optLocation = locationUseCase.findById(id);
        if (optLocation.isEmpty()) {
            throw new NotFoundException(format("Location ID %d not found", id));
        }
        return optLocation.get();
    }

    public Building findBuildingById(Long id) {
        var optBuilding = buildingUseCase.findById(id);
        if (optBuilding.isEmpty()) {
            throw new NotFoundException(format("Building ID %d not found", id));
        }
        return optBuilding.get();
    }
}
