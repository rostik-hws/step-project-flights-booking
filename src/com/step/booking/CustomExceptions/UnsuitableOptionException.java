package com.step.booking.CustomExceptions;

public class UnsuitableOptionException extends RuntimeException {
        public UnsuitableOptionException (String message) {
            super(message);
        }
}
