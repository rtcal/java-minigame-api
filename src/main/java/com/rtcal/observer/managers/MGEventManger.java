package com.rtcal.observer.managers;

import com.rtcal.observer.event.MGEvent;
import com.rtcal.observer.event.MGEventHandler;
import com.rtcal.observer.event.MGListener;
import com.rtcal.exceptions.MGEventException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public final class MGEventManger {

    private static final Map<Class<? extends MGEvent>, List<MGRegisteredListener>> handlers = new HashMap<>();

    private final MGEventLogger logger;

    public MGEventManger(MGEventLogger logger) {
        this.logger = logger;
    }

    public MGEventManger() {
        this((msg) -> System.out.println("[MGEventLogger] " + msg));
    }

    public void callEvent(MGEvent event) {
        if (!handlers.containsKey(event.getClass())) return;

        for (MGRegisteredListener listener : handlers.get(event.getClass())) {
            try {
                listener.callEvent(event);
            } catch (MGEventException e) {
                logger.log(e.getMessage());
            }
        }
    }

    public void registerEventListener(MGListener listener) {
        for (Map.Entry<Class<? extends MGEvent>, Set<MGRegisteredListener>> entry : createRegisteredListeners(listener).entrySet()) {
            handlers.computeIfAbsent(entry.getKey(), k -> new ArrayList<>()).addAll(entry.getValue());
            Collections.sort(handlers.get(entry.getKey()));
        }
    }

    private Map<Class<? extends MGEvent>, Set<MGRegisteredListener>> createRegisteredListeners(MGListener listener) {
        Map<Class<? extends MGEvent>, Set<MGRegisteredListener>> localHandlers = new HashMap<>();

        if (listener == null) return localHandlers;

        Method[] methods;

        try {
            methods = listener.getClass().getMethods();
        } catch (NoClassDefFoundError e) {
            return localHandlers;
        }

        for (final Method method : methods) {
            final MGEventHandler eventHandler = method.getAnnotation(MGEventHandler.class);

            if (eventHandler == null) continue;
            if (method.isBridge() || method.isSynthetic()) continue;

            final Class<?> checkClass;

            if (method.getParameterTypes().length != 1 || !MGEvent.class.isAssignableFrom(checkClass = method.getParameterTypes()[0])) {
                logger.log("Attempted to register an invalid MGEventHandler method signature " + method.toGenericString() + " in " + listener.getClass());
                continue;
            }

            final Class<? extends MGEvent> eventClass = checkClass.asSubclass(MGEvent.class);

            method.setAccessible(true);

            Set<MGRegisteredListener> registeredListenerSet = localHandlers.computeIfAbsent(eventClass, k -> new HashSet<>());

            MGEventExecutor executor = (l, e) -> {
                try {
                    if (!eventClass.isAssignableFrom(e.getClass())) return;
                    method.invoke(l, e);
                } catch (InvocationTargetException ex) {
                    throw new MGEventException(ex.getCause());
                } catch (Throwable t) {
                    throw new MGEventException(t);
                }
            };

            registeredListenerSet.add(new MGRegisteredListener(listener, executor, eventHandler.priority(), eventHandler.ignoreCancelled()));
        }

        return localHandlers;
    }

}
