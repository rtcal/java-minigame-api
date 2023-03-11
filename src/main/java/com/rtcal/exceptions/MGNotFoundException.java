package com.rtcal.exceptions;

public class MGNotFoundException extends Exception {
    public MGNotFoundException() {
        super("MGNotFoundException thrown");
    }

    public MGNotFoundException(String message) {
        super(message);
    }

    public MGNotFoundException(Throwable cause) {
        super(cause);
    }

    public MGNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}