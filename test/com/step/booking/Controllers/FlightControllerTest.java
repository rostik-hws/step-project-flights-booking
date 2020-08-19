package com.step.booking.Controllers;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class FlightControllerTest {

    FlightController flightController = new FlightController();
    Storage storage = new Storage();

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @Test
    void getFlightByNumber() {
        storage.getFlightController().generateFlights(true);
        storage.getFlightController().saveDataToDb();
        storage.getFlightController().getFlightByNumber(1030);
        String expectedFormat = "ID: 1030 \\| Destination: ([^\\s]+) \\| Date: ([^\\s]+) \\| Time: ([^\\s]+) \\| Capacity: ([^\\s]+)\n";
        Assert.assertTrue((output.toString()).matches(expectedFormat));
    }

    @Test
    void printAllFlights() {
        storage.getFlightController().generateFlights(true);
        storage.getFlightController().saveDataToDb();
        storage.getFlightController().printAllFlights();
        String expectedString = "\\| ([^\\s]+)   \\| ([^\\s]+) \\| ([^\\s]+)\\s*\\| ([^\\s]+) \\|\n";
        String expectedTable = new String(new char[50]).replace("\0", expectedString);
        Assert.assertTrue(output.toString().matches(expectedTable));
    }

    @AfterEach
    public void cleanUpStreams() {
        System.setOut(null);
    }
}