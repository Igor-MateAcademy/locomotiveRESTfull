package com.ua.locomotive.exceptions;

public class DriverNotFoundException extends Exception {

    public DriverNotFoundException(String message) {
        super(message);
    }

    public DriverNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public synchronized Throwable initCause(Throwable cause) {
        return super.initCause(cause);
    }
}
