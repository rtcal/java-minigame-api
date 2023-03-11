package com.rtcal.game;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class MGGameModeManager {

    private static volatile MGGameModeManager instance;

    private final Set<String> gameModes = ConcurrentHashMap.newKeySet();

    private MGGameModeManager() {

    }

    public static MGGameModeManager getInstance() {
        if (instance == null) {
            synchronized (MGGameModeManager.class) {
                if (instance == null) {
                    instance = new MGGameModeManager();
                }
            }
        }

        return instance;
    }

    public void register(@NotNull String type) {
        gameModes.add(type.toLowerCase());
    }

    public void unregister(@NotNull String type) {
        gameModes.remove(type.toLowerCase());
    }

    public boolean isType(@NotNull String type) {
        return gameModes.contains(type.toLowerCase());
    }

    public Set<String> getGameModes() {
        return Collections.unmodifiableSet(gameModes);

    }

}
