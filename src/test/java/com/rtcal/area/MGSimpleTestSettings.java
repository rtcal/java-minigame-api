package com.rtcal.area;

public class MGSimpleTestSettings extends MGAreaSettings {

    private final String message;

    public MGSimpleTestSettings(int priority, String message) {
        super(priority);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
