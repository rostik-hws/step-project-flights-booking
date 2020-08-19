package com.step.booking.Controllers;

import com.step.booking.Entities.Flight;
import com.step.booking.Services.FlightService;

import java.util.Comparator;
import java.util.List;

import static com.step.booking.Constants.CommonFormats.*;
import static com.step.booking.Constants.CommonUtils.dateLongToString;

public class FlightController {
   final private FlightService flightService = new FlightService();

   public List<Flight> getAll () {
       return flightService.getAll();
   }

   public Flight getFlight (int index) {
       return flightService.getOne(index);
   }

   public void generateFlights (boolean shallRewrite) {
       flightService.generateFlights(shallRewrite);
   }

   public void saveDataToFile (List<Flight> flightList) {
        flightService.saveData(flightList);
   }

   public void saveDataToDb () {
       flightService.saveDataToDb();
   }

    public void getFlightByNumber(int id) {
        if (id >= 1000 && id < flightService.getAll().size() + 1000) {
            flightService
                    .getAll()
                    .stream()
                    .filter(e -> e.getFlightNumber() == id)
                    .findFirst().ifPresent(flight -> System.out.println(flight.toTableFormat()));
        } else {
            System.out.println("Sorry no such flight id");
        }
    }

   public void printAllFlights () {
        flightService
                .getAll()
                .stream()
                .sorted(Comparator.comparingLong(Flight::getDepartureDateTime))
                .forEach(e -> System.out.printf(
                        TABLE_FORMAT,
                        e.getFlightNumber(),
                        dateLongToString(e.getDepartureDateTime(), DATE_FORMAT),
                        e.getDestination(),
                        dateLongToString(e.getDepartureDateTime(), TIME_FORMAT)
                        )
                );
    }

    public List<Flight> getFlightsByParameters(String destination, String date, int passengersNumber) {
        if (flightService.getFlightsByParameters(destination, date, passengersNumber).size() != 0) {
            flightService
                    .getFlightsByParameters(destination, date, passengersNumber)
                    .forEach(e -> System.out.println((
                            flightService
                                    .getFlightsByParameters(destination, date, passengersNumber)
                                    .indexOf(e) + 1) + ". " + e.toTableFormat()));
            return flightService.getFlightsByParameters(destination, date, passengersNumber);
        } else {
            System.out.println("По заданным параметрам ничего не найдено");
            return null;
        }
    }

}
