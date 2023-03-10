package com.rtcal.game.arena;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class MGArenaType {

    private static volatile MGArenaType instance;

    private final Set<String> types = ConcurrentHashMap.newKeySet();
    private final Lock lock = new ReentrantLock();

    private MGArenaType() {

    }

    public static MGArenaType getInstance() {
        if (instance == null) {
            synchronized (MGArenaType.class) {
                if (instance == null) {
                    instance = new MGArenaType();
                }
            }
        }

        return instance;
    }

    public void register(@NotNull String type) {
        types.add(type);
    }

    public void unregister(@NotNull String type) {
        types.remove(type);
    }

    public boolean isType(@NotNull String type) {
        return types.contains(type);
    }

    public Set<String> getTypes() {
        return Collections.unmodifiableSet(types);

    }

}
