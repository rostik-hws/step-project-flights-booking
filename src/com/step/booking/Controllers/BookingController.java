package com.step.booking.Controllers;

import com.step.booking.Entities.Booking;
import com.step.booking.Entities.Customer;
import com.step.booking.Entities.Flight;
import com.step.booking.Services.BookingService;
import com.step.booking.Services.FlightService;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BookingController {
    final private BookingService bookingService = new BookingService();

    public List<Booking> getAll() {
        return bookingService.getAll();
    }

    public void save(Booking booking) {
        bookingService.save(booking);
    }

    public Booking getFlight(int index) {
        return bookingService.getOne(index);
    }

    public void saveDataToFile(List<Booking> bookingsList) {
        bookingService.saveData(bookingsList);
    }

    public void saveDataToDb() {
        bookingService.saveDataToDb();
    }

    public Booking deleteBookingByBookingNumber(long bookingNumber) {
        Booking booking = bookingService.getAll()
                .stream()
                .filter(e -> e.getNumber() == bookingNumber)
                .findAny().orElse(null);
        if (booking == null) {
            return null;
        } else {
            bookingService.remove(booking);
            System.out.println("Deleted");
            return booking;
        }
    }

    public void getAllUsersBookings(String name, String surname) {
        bookingService.getAllUsersBookings(name, surname).forEach(
                e ->
                        System.out.println(
                                bookingService.getAllUsersBookings(name, surname).indexOf(e) + 1 + ". " + e)
        );
    }

}
