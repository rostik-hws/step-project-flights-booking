package com.step.booking.Console;

import com.step.booking.Controllers.Storage;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import static com.step.booking.Constants.CommonFormats.*;

public class OnlineBoard {
    public void run (Storage storage) {
        final String DASHES = new String(new char[64]).replace("\0", "-");

        System.out.printf("%-64s\n", "Online Table Airport: Kiev Boryspil, "
                + LocalDateTime.now(ZoneId.of(TIME_ZONE))
                .format(DateTimeFormatter
                        .ofPattern(DATE_TIME_FORMAT)));

        System.out.printf("%s\n", DASHES);
        System.out.printf(TABLE_FORMAT, "Flight", "Date", "Destination", "Time");
        System.out.printf("%s\n", DASHES);

        storage.getFlightController().printAllFlights();

        System.out.printf("%s\n", DASHES);
    }
}
