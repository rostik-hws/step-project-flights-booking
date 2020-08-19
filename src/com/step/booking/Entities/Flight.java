package com.step.booking.Entities;

import com.step.booking.Constants.CommonFormats;
import com.step.booking.Constants.CommonUtils;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;

public class Flight implements Serializable {

    private int flightNumber;
    private long departureDateTime;
    private String destination;
    private int planeCapacity;
    private List<Customer> passengers;

    public Flight(int flightNumber, long departureDateTime, String destination, int planeCapacity, List<Customer> passengers) {
        this.flightNumber = flightNumber;
        this.departureDateTime = departureDateTime;
        this.destination = destination;
        this.planeCapacity = planeCapacity;
        this.passengers = passengers;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public long getDepartureDateTime() {
        return departureDateTime;
    }

    public String getDestination() {
        return destination;
    }

    public int getPlaneCapacity() {
        return planeCapacity;
    }

    public List<Customer> getPassengers() {
        return passengers;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setDepartureDateTime(long departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setPlaneCapacity(int planeCapacity) {
        this.planeCapacity = planeCapacity;
    }

    public void setPassengers(List<Customer> passengers) {
        this.passengers = passengers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return flightNumber == flight.flightNumber &&
                departureDateTime == flight.departureDateTime &&
                destination.equals(flight.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightNumber, departureDateTime, destination);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightNumber=" + flightNumber +
                ", departureDateTime=" + departureDateTime +
                ", destination='" + destination + '\'' +
                ", planeCapacity=" + planeCapacity +
                ", passengers=" + passengers +
                '}';
    }

    public String toTableFormat() {
        return "ID: " + flightNumber + " | Destination: " + destination
                + " | Date: " + CommonUtils.dateLongToString(departureDateTime, "dd/MM/yyyy")
                + " | Time: " + CommonUtils.dateLongToString(departureDateTime, "HH:mm")
                + " | Capacity: " + planeCapacity;
    }
}
