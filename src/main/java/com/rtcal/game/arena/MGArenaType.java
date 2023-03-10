package com.rtcal.game.arena;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class MGArenaType {

    private static volatile MGArenaType instance;

    private final Set<String> types = new HashSet<>();
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
        lock.lock();
        try {
            types.add(type);
        } finally {
            lock.unlock();
        }
    }

    public void unregister(@NotNull String type) {
        lock.lock();
        try {
            types.remove(type);
        } finally {
            lock.unlock();
        }
    }

    public boolean isType(@NotNull String type) {
        lock.lock();
        try {
            return types.contains(type);
        } finally {
            lock.unlock();
        }
    }

    public Set<String> getTypes() {
        lock.lock();
        try {
            return new HashSet<>(types);
        } finally {
            lock.unlock();
        }
    }

}
