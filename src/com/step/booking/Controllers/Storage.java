package com.step.booking.Controllers;

public class Storage {
        private final FlightController flightController = new FlightController();
        private final BookingController bookingController = new BookingController();

        public FlightController getFlightController() {
            return this.flightController;
        }
        public BookingController getBookingController() {
            return this.bookingController;
        }
}
