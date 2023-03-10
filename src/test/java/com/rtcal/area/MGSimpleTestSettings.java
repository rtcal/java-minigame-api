package com.rtcal.area;

import com.rtcal.game.area.MGAreaSettings;

public class MGSimpleTestSettings extends MGAreaSettings {

    private final String message;

    public MGSimpleTestSettings(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
