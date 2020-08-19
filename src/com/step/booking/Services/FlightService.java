package com.step.booking.Services;

import com.step.booking.Constants.CommonUtils;
import com.step.booking.DAO.CollectionFlightDao;
import com.step.booking.DAO.DAO;
import com.step.booking.Entities.Flight;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.step.booking.Constants.FilePaths.FILEPATH_FLIGHTS;

public class FlightService {

    final private DAO<Flight> flightDao = new CollectionFlightDao();

    public void save(Flight flight) {
        flightDao.save(flight);
    }

    public Flight getOne(int index) {
        return flightDao.getOne(index);
    }

    public List<Flight> getAll() {
        return flightDao.getAll();
    }

    public void saveData(List<Flight> flights) {
        flightDao.saveData(flights);
    }

    public void saveDataToDb() {
        flightDao.saveDataToDb();
    }

    public void generateFlights(boolean shallRewrite) {

        String dirPath = "./files";
        File directory = new File(dirPath);
        if (!directory.exists()) {
            directory.mkdir();
        }

        final int flightsNumber = 50;
        File newFile = new File(FILEPATH_FLIGHTS);
        ArrayList<Flight> tempFlightsList = new ArrayList<>();
        ArrayList<String> citiesList = new ArrayList<String>() {{
            add("Токио");
            add("Нью-Йорк");
            add("Львов");
            add("Лондон");
            add("Чикаго");
            add("Киото");
            add("Париж");
            add("Марсель");
            add("Барселона");
            add("Минск");
            add("Рио-де-Жанейро");
            add("Лос-Анджелес");
            add("Чикаго");
            add("Одесса");
            add("Осака");
            add("Пекин");
            add("Мадрид");
            add("Лондон");
            add("Ливерпуль");
            add("Дублин");
        }};

            if (newFile.length() == 0 || shallRewrite) {
                for (int i = 0; i < flightsNumber; i++) {
                    tempFlightsList.add(
                            new Flight(
                                    i + 1000,
                                    CommonUtils.getNearFutureTime(CommonUtils.generateRandomIntInRange(1800, 86400)),
                                    citiesList.get(CommonUtils.generateRandomIntInRange(0, 19)),
                                    300,
                                    new ArrayList<>()
                            )
                    );
                }
                saveData(tempFlightsList);
            } else {
                System.out.println("Flights database is ready");
            }
        }

        public List<Flight> getFlightsByParameters (String destination, String date, int passengersNumber) {
            return flightDao
                    .getAll()
                    .stream()
                    .filter(
                            item -> item.getDestination().equalsIgnoreCase(destination) &&
                                    CommonUtils.getDepartureDate(item.getDepartureDateTime()).equalsIgnoreCase(date.trim()) &&
                                    ((item.getPlaneCapacity() - item.getPassengers().size()) >= passengersNumber)
                    )
                    .sorted(Comparator.comparingLong(Flight::getDepartureDateTime))
                    .collect(Collectors.toList());
    }

}
