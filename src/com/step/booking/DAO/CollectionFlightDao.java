package com.step.booking.DAO;

import com.step.booking.Entities.Flight;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import static com.step.booking.Constants.FilePaths.FILEPATH_FLIGHTS;

public class CollectionFlightDao implements DAO<Flight> {
    final private ArrayList<Flight> flightsList;

    public CollectionFlightDao() {
        flightsList = new ArrayList<>();
    }

    @Override
    public void save(Flight flight) {
        flightsList.add(flight);
    }

    @Override
    public Flight getOne(int index) {
        return flightsList.get(index);
    }

    @Override
    public List<Flight> getAll() {
        return flightsList;
    }

    @Override
    public boolean remove(int index) {
        int beforeRemoveSize = flightsList.size();
        flightsList.remove(index);
        int afterRemoveSize = flightsList.size();
        return beforeRemoveSize - 1 == afterRemoveSize;
    }

    @Override
    public boolean remove(Flight flight) {
        int beforeRemoveSize = flightsList.size();
        flightsList.remove(flight);
        int afterRemoveSize = flightsList.size();
        return beforeRemoveSize - 1 == afterRemoveSize;
    }

    @Override
    public void saveData(List<Flight> flights) {
        try (ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(FILEPATH_FLIGHTS))) {
            objectOut.writeObject(flights);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveDataToDb() {
        try (ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(FILEPATH_FLIGHTS))) {
            Object flightsObj = objectIn.readObject();
            @SuppressWarnings("unchecked")
            List<Flight> familyList = (List<Flight>) flightsObj;
            familyList.forEach(this::save);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
