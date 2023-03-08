package com.rtcal.observer.managers;

import com.rtcal.observer.event.MGEvent;
import com.rtcal.observer.event.MGListener;
import com.rtcal.exceptions.MGEventException;

public interface MGEventExecutor {

    void execute(MGListener listener, MGEvent event) throws MGEventException;

}
