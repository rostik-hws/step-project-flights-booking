package com.step.booking.Console;

import java.util.ArrayList;

public class MainMenu {
    public static ArrayList<String> mainMenu = new ArrayList<String>() {
        {
            add("1. Show online board");
            add("2. Get flight information");
            add("3. Get new flights [Overwrite online board]");
            add("4. Make a book on flight");
            add("5. Cancel booking");
            add("6. Show all my bookings");
            add("7. Exit");
        }
    };
}
