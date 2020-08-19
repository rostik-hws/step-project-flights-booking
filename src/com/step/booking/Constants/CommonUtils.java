package com.step.booking.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Random;

import static com.step.booking.Constants.CommonFormats.DATE_FORMAT;
import static com.step.booking.Constants.CommonFormats.TIME_ZONE;

public class CommonUtils {
    public static long getCurrentDateTime() {
        return Instant.now().toEpochMilli();
    }

    public static long getNearFutureTime(int offset) {
        return Instant.now().toEpochMilli() + Instant.ofEpochSecond(offset).toEpochMilli();
    }

    public static int generateRandomIntInRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static long dateTimeToLong(LocalDateTime dateTime) {
        ZoneOffset zoneOffset = dateTime.atZone(ZoneId.of("Europe/Kiev")).getOffset();
        return dateTime.toInstant(zoneOffset).toEpochMilli();
    }

    public static String dateLongToString(Long dateTime, String format) {
        return Instant.ofEpochMilli(dateTime)
                .atZone(ZoneId.of("Europe/Kiev"))
                .toLocalDateTime()
                .truncatedTo(ChronoUnit.HOURS)
                .plusMinutes(
                        15 * (Instant.ofEpochMilli(dateTime)
                        .atZone(ZoneId.of("Europe/Kiev"))
                        .toLocalDateTime()
                        .truncatedTo(ChronoUnit.MINUTES).getMinute() / 15)
                )
                .format(DateTimeFormatter.ofPattern(format));
    }

    public static long stringDateToMilliseconds (String date) {
        long milliseconds = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date localDate = formatter.parse(date);
            milliseconds = localDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return milliseconds;
    }

    public static String getDepartureDate(long localDateTime) {
        return Instant
                .ofEpochMilli(localDateTime)
                .atZone(ZoneId.of("Europe/Kiev"))
                .toLocalDate()
                .format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }
}
