package com.rtcal.observer.managers;

import com.rtcal.observer.event.MGCancellable;
import com.rtcal.observer.event.MGEvent;
import com.rtcal.observer.event.MGListener;
import com.rtcal.observer.exceptions.MGEventException;

public record MGRegisteredListener(MGListener listener, MGEventExecutor executor, short priority, boolean ignoreCancelled) implements Comparable<MGRegisteredListener> {

    public void callEvent(MGEvent event) throws MGEventException {
        if (event instanceof MGCancellable) {
            if (((MGCancellable) event).isCancelled() && !ignoreCancelled) return;
        }

        executor.execute(listener, event);
    }

    @Override
    public int compareTo(MGRegisteredListener other) {
        return Short.compare(priority, other.priority);
    }
}
