package com.step.booking.Console;

import com.step.booking.Controllers.Storage;
import com.step.booking.Entities.Booking;
import com.step.booking.Entities.Customer;
import com.step.booking.Entities.Flight;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BookingCmds {
    Scanner in = new Scanner(System.in);

    public void run(Storage storage) {
        try {
            System.out.print("Введите место назначения: ");
            String destination = in.nextLine().trim();
            System.out.print("Введите дату (в формате DD/MM/YYYY): ");
            String date = in.nextLine().trim();
            System.out.print("Введите желаемое количество билетов: ");
            int ticketsQuantity = in.nextInt();
            in.nextLine();
            List<Flight> flightsArrayList = storage.getFlightController().getFlightsByParameters(destination, date, ticketsQuantity);
            System.out.print("Введите номер из списка (или 0 для выхода в главное меню): ");
            int desiredFlight = in.nextInt();
            in.nextLine();
            if (desiredFlight == 0) {
                System.out.println("Вы в главном меню");
            } else {
                Flight chosenFlight = flightsArrayList.get(desiredFlight - 1);
                System.out.println("Введите имя и фамилию для всех пассажиров");
                for (int i = 0; i < ticketsQuantity; i++) {
                    System.out.print("Введите имя для " + (i + 1) + "-го пассажира: ");
                    String name = in.nextLine().trim();
                    System.out.print("Введите фамилию для " + (i + 1) + "-го пассажира: ");
                    String surname = in.nextLine().trim();
                    Customer customer = new Customer(name, surname);
                    chosenFlight.getPassengers().add(customer);
                }
                chosenFlight.setPlaneCapacity(chosenFlight.getPlaneCapacity() - ticketsQuantity);
                Booking booking = new Booking(chosenFlight, (ArrayList<Customer>) chosenFlight.getPassengers());
                storage.getBookingController().save(booking);
                System.out.println("Бронирование сделано");
            }
        } catch (InputMismatchException e) {
            in.nextLine();
            System.out.println("Ошибка: вы в главном меню");
            e.printStackTrace();
        }
    }
}
