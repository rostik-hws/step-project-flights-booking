package com.step.booking.DAO;

import com.step.booking.Constants.CommonUtils;
import com.step.booking.Entities.Flight;
import org.junit.jupiter.api.Test;
import org.junit.*;

import java.io.File;
import java.util.ArrayList;

import static com.step.booking.Constants.FilePaths.FILEPATH_FLIGHTS;
import static org.junit.jupiter.api.Assertions.*;

class CollectionFlightDaoTest {

    private final CollectionFlightDao collectionFlightDao = new CollectionFlightDao();

            Flight flight = new Flight(1111,
            12023213232L,
            "London",
            300,
            new ArrayList<>()
            );

    @Test
    void saveTest() {
        int initialSize = collectionFlightDao.getAll().size();
        int expectedSize = 1;
        collectionFlightDao.save(flight);
        Assert.assertEquals(initialSize + 1, expectedSize);
    }

    @Test
    void getOneTest() {
        collectionFlightDao.save(flight);
        String expectedFlight = "Flight{flightNumber=1111, departureDateTime=12023213232, destination='London', planeCapacity=300, passengers=[]}";
        Assert.assertEquals(expectedFlight, collectionFlightDao.getOne(0).toString());
    }

    @Test
    void getAllTest() {
        collectionFlightDao.save(flight);
        String expectedFlightList = "[Flight{flightNumber=1111, departureDateTime=12023213232, destination='London', planeCapacity=300, passengers=[]}]";
        Assert.assertEquals(expectedFlightList, collectionFlightDao.getAll().toString());
    }

    @Test
    void removeTest() {
        int expectedSize = 0;
        collectionFlightDao.save(flight);
        collectionFlightDao.remove(0);
        Assert.assertEquals(expectedSize, collectionFlightDao.getAll().size());
    }

    @Test
    void removeByFlightTest() {
        int expectedSize = 0;
        collectionFlightDao.save(flight);
        collectionFlightDao.remove(flight);
        Assert.assertEquals(expectedSize, collectionFlightDao.getAll().size());
    }

    @Test
    void saveDataTest() {
        collectionFlightDao.save(flight);
        collectionFlightDao.saveData(collectionFlightDao.getAll());
        File newFile = new File(FILEPATH_FLIGHTS);
        Assert.assertTrue(newFile.length() > 0);
    }

    @Test
    public void saveDataToDb() {
        collectionFlightDao.save(flight);
        collectionFlightDao.saveDataToDb();
        String expectedFlightList = "[Flight{flightNumber=1111, departureDateTime=12023213232, destination='London', planeCapacity=300, passengers=[]}]";
        Assert.assertEquals(expectedFlightList, collectionFlightDao.getAll().toString());
    }
}