package com.rtcal.observer.event;

public abstract class MGEvent {

    public String getName() {
        return this.getClass().getSimpleName();
    }

}
