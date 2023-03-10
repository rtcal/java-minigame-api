package com.rtcal.exceptions;

public class MGPlayerBusyException extends Exception {

    public MGPlayerBusyException() {
        super("MGPlayer is busy");
    }

    public MGPlayerBusyException(String message) {
        super(message);
    }

    public MGPlayerBusyException(Throwable cause) {
        super(cause);
    }

    public MGPlayerBusyException(String message, Throwable cause) {
        super(message, cause);
    }

}
