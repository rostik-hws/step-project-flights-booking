package com.step.booking.Services;

import com.step.booking.Entities.Flight;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static com.step.booking.Constants.FilePaths.FILEPATH_FLIGHTS;
import static org.junit.jupiter.api.Assertions.*;

class FlightServiceTest {

    final private FlightService flightService = new FlightService();

    Flight flight = new Flight(1111,
            12023213232L,
            "London",
            300,
            new ArrayList<>()
    );

    @Test
    void generateFlightsIfShallRewrite() {
        long fileBeforeModify = new File(FILEPATH_FLIGHTS).lastModified();
        flightService.generateFlights(true);
        long fileAfterModify = new File(FILEPATH_FLIGHTS).lastModified();
        Assert.assertNotEquals(fileBeforeModify, fileAfterModify);
    }

    @Test
    void generateFlightsIfShallNotRewrite() {
        long fileBeforeModify = new File(FILEPATH_FLIGHTS).lastModified();
        flightService.generateFlights(false);
        long fileAfterModify = new File(FILEPATH_FLIGHTS).lastModified();
        if (new File(FILEPATH_FLIGHTS).length() != 0) {
            Assert.assertEquals(fileBeforeModify, fileAfterModify);
        }
    }
}