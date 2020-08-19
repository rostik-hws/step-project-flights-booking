package com.step.booking.Entities;

import com.step.booking.Constants.CommonUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Booking implements Serializable {
    private final long number;
    private final LocalDateTime dateTime = LocalDateTime.now();
    private final List<Customer> passengers;
    private final Flight flight;

    public Booking(Flight flight, ArrayList<Customer> passengers) {
        this.number = setBookingNumber();
        this.passengers = passengers;
        this.flight = flight;
    }

    public long getNumber() {
        return number;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public List<Customer> getPassengers() {
        return passengers;
    }

    public Flight getFlight() {
        return flight;
    }

    private long setBookingNumber() {
        return dateTime.getYear() * 10000000000L +
                dateTime.getMonth().getValue() * 100000000 +
                dateTime.getDayOfMonth() * 1000000 +
                dateTime.getHour() * 10000 +
                dateTime.getMinute() * 100 +
                dateTime.getSecond();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return number == booking.number &&
                dateTime.equals(booking.dateTime) &&
                passengers.equals(booking.passengers) &&
                flight.equals(booking.flight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, dateTime, passengers, flight);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        passengers.forEach(e -> result.append(e.toString()));
        return "Booking - [ ID: " + number + " | Destination: " + flight.getDestination()
                + " | Date: " + CommonUtils.dateLongToString(flight.getDepartureDateTime(), "dd/MM/yyyy")
                + " | Time: " + CommonUtils.dateLongToString(flight.getDepartureDateTime(), "HH:mm")
                + " | Passengers: " + result + "]";
    }
}
