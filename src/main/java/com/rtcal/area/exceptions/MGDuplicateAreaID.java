package com.rtcal.area.exceptions;

public class MGDuplicateAreaID extends Exception {
    public MGDuplicateAreaID() {
        super("Area with UUID or Name supplied already exists");
    }

    public MGDuplicateAreaID(String message) {
        super(message);
    }

    public MGDuplicateAreaID(Throwable cause) {
        super(cause);
    }

    public MGDuplicateAreaID(String message, Throwable cause) {
        super(message, cause);
    }
}