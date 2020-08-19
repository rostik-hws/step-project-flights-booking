package com.step.booking.DAO;

import com.step.booking.Entities.Booking;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.step.booking.Constants.FilePaths.FILEPATH_BOOKINGS;

public class CollectionBookingDAO implements DAO<Booking> {
    final private ArrayList<Booking> bookingsList;

    public CollectionBookingDAO() {
        bookingsList = new ArrayList<>();
    }

    @Override
    public void save(Booking booking) {
        bookingsList.add(booking);
    }

    @Override
    public Booking getOne(int index) {
        return bookingsList.get(index);
    }

    @Override
    public List<Booking> getAll() {
        return bookingsList;
    }

    @Override
    public boolean remove(int index) {
        int beforeRemoveSize = bookingsList.size();
        bookingsList.remove(index);
        int afterRemoveSize = bookingsList.size();
        return beforeRemoveSize - 1 == afterRemoveSize;
    }

    @Override
    public boolean remove(Booking booking) {
        int beforeRemoveSize = bookingsList.size();
        bookingsList.remove(booking);
        int afterRemoveSize = bookingsList.size();
        return beforeRemoveSize - 1 == afterRemoveSize;
    }

    @Override
    public void saveData(List<Booking> bookings) {
        if (bookings.size() != 0) {
            try (ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(FILEPATH_BOOKINGS))) {
                objectOut.writeObject(bookings);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveDataToDb() {
        File newFile = new File(FILEPATH_BOOKINGS);
        if (newFile.length() != 0) {
            try (ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(FILEPATH_BOOKINGS))) {
                Object bookingObj = objectIn.readObject();
                @SuppressWarnings("unchecked")
                List<Booking> bookingList = (List<Booking>) bookingObj;
                bookingList.forEach(this::save);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}