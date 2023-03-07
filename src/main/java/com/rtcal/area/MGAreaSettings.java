package com.rtcal.area;

public abstract class MGAreaSettings {

    private final int priority;

    public MGAreaSettings(final int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

}
