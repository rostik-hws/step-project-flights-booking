package com.step.booking;

import com.step.booking.Console.BookingCmds;
import com.step.booking.CustomExceptions.UnsuitableOptionException;
import com.step.booking.Console.MainMenu;
import com.step.booking.Console.OnlineBoard;
import com.step.booking.Controllers.Storage;
import com.step.booking.Entities.Booking;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleApp {

    Storage storage = new Storage();
    OnlineBoard onlineBoard = new OnlineBoard();
    BookingCmds bookingCmds = new BookingCmds();
    transient Scanner in = new Scanner(System.in);
    transient Scanner externalIn = new Scanner(System.in);
    Boolean isExit = false;

    public void start() {
        storage.getFlightController().generateFlights(false);
        storage.getFlightController().saveDataToDb();
        storage.getBookingController().saveDataToDb();

        onlineBoard.run(storage);
        System.out.println("Enter any number from the list (without dot or any other symbol): ");
        System.out.println("Don't forget to press 'exit' before leave to save all changes :)");
        MainMenu.mainMenu.forEach(System.out::println);

        do {
            String mainMenu = externalIn.nextLine().trim().toUpperCase();
            try {
                switch (mainMenu) {
                    case ("1"): {
                        onlineBoard.run(storage);
                        MainMenu.mainMenu.forEach(System.out::println);
                        break;
                    }
                    case ("2"): {
                        System.out.print("Enter flight ID: ");
                        int id = in.nextInt();
                        storage.getFlightController().getFlightByNumber(id);
                        break;
                    }
                    case ("3"): {
                        storage.getFlightController().generateFlights(true);
                        System.out.println("It's done, rerun app to get new information.");
                        isExit = true;
                        break;
                    }
                    case ("4"): {
                        bookingCmds.run(storage);
                        MainMenu.mainMenu.forEach(System.out::println);
                        break;
                    }
                    case ("5"): {
                        System.out.print("Введите номер бронирования: ");
                        long id = in.nextLong();
                        Booking booking = storage.getBookingController().deleteBookingByBookingNumber(id);
                        storage.getFlightController().getFlight(
                                storage.getFlightController().getAll().indexOf(booking.getFlight()))
                                .setPlaneCapacity(
                                                booking.getFlight().getPlaneCapacity() +
                                                booking.getPassengers().size()
                                );
                        MainMenu.mainMenu.forEach(System.out::println);
                        break;
                    }
                    case ("6"): {
                        System.out.print("Введите имя: ");
                        String name = in.nextLine();
                        System.out.print("Введите фамилию: ");
                        String surname = in.nextLine();
                        storage.getBookingController().getAllUsersBookings(name, surname);
                        break;
                    }
                    case ("7"): {
                        storage.getFlightController().saveDataToFile(storage.getFlightController().getAll());
                        storage.getBookingController().saveDataToFile(storage.getBookingController().getAll());
                        isExit = true;
                        break;
                    }
                    default:
                        throw new UnsuitableOptionException("Вероятно такого пункта меню не существует, попробуйте номер из списка");
                }
            } catch (UnsuitableOptionException e) {
                e.printStackTrace();
            } catch (InputMismatchException e) {
                in.nextLine();
                e.printStackTrace();
            } catch (NullPointerException e) {
                in.nextLine();
                System.out.println("Вероятно вы ввели несуществующий id, попробуйте снова");
            }
        } while (!isExit);
        System.out.println("Goodbye :)");
    }
}
