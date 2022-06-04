package com.ua.locomotive.exceptions;

public class LocomotiveNotFoundException extends Exception {

    public LocomotiveNotFoundException(String message) {
        super(message);
    }

    public LocomotiveNotFoundException(String message, Throwable cause) {
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
