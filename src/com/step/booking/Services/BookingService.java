package com.step.booking.Services;

import com.step.booking.DAO.CollectionBookingDAO;
import com.step.booking.DAO.DAO;
import com.step.booking.Entities.Booking;
import com.step.booking.Entities.Customer;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BookingService {
    final private DAO<Booking> bookingDao = new CollectionBookingDAO();

    public void save(Booking booking) {
        bookingDao.save(booking);
    }

    public Booking getOne(int index) {
        return bookingDao.getOne(index);
    }

    public List<Booking> getAll() {
        return bookingDao.getAll();
    }

    public void saveData(List<Booking> bookings) {
        bookingDao.saveData(bookings);
    }

    public void saveDataToDb() {
        bookingDao.saveDataToDb();
    }

    public void remove(Booking booking) {
        bookingDao.remove(booking);
    }

    public List<Booking> getAllUsersBookings (String name, String surname) {
        Predicate<Customer> containsUser = e -> e.getName().equalsIgnoreCase(name) && e.getSurname().equalsIgnoreCase(surname);
        List<Booking> bookingsList = bookingDao.getAll()
                .stream()
                .filter(e -> e.getPassengers().stream().anyMatch(containsUser))
                .collect(Collectors.toList());
        if (bookingsList.size() == 0) {
            System.out.println("У такого пользователя нет бронирований");
        }
        return bookingsList;
    }

}
