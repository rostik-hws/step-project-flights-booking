package com.step.booking.DAO;

import java.util.List;

public interface DAO<T> {
    void save(T item);
    T getOne(int index);
    List<T> getAll();
    boolean remove(int index);
    boolean remove(T item);
    void saveData(List<T> items);
    void saveDataToDb();
}
