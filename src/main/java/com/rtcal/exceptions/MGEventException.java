package com.rtcal.exceptions;

public class MGEventException extends Exception {

    private final Throwable cause;

    public MGEventException(Throwable cause) {
        this.cause = cause;
    }

    public MGEventException() {
        this.cause = null;
    }

    public MGEventException(Throwable cause, String message) {
        super(message);
        this.cause = cause;
    }

    @Override
    public Throwable getCause() {
        return cause;
    }
}
