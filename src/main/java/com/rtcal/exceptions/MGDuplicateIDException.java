package com.rtcal.exceptions;

public class MGDuplicateIDException extends Exception {
    public MGDuplicateIDException() {
        super("MGIdentifier supplied already exists");
    }

    public MGDuplicateIDException(String message) {
        super(message);
    }

    public MGDuplicateIDException(Throwable cause) {
        super(cause);
    }

    public MGDuplicateIDException(String message, Throwable cause) {
        super(message, cause);
    }
}