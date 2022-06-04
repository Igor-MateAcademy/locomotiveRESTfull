package com.ua.locomotive.exceptions;

public class TripNotFoundException extends Exception {

    public TripNotFoundException(String message) {
        super(message);
    }

    public TripNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public synchronized Throwable getCause() {
        return super.getCause();
    }
}
