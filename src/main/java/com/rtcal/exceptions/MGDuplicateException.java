package com.rtcal.exceptions;

public class MGDuplicateException extends Exception {
    public MGDuplicateException() {
        super("MGDuplicationException thrown");
    }

    public MGDuplicateException(String message) {
        super(message);
    }

    public MGDuplicateException(Throwable cause) {
        super(cause);
    }

    public MGDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }
}