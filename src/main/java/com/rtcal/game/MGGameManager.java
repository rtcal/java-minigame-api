package com.rtcal.game;

import com.rtcal.game.arena.MGGame;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * MGGameManager holds instances of available created games
 */
public final class MGGameManager {

    private static volatile MGGameManager instance;

    private static final Map<UUID, MGGame> registeredGames = new ConcurrentHashMap<>();

    private MGGameManager() {

    }

    public static MGGameManager getInstance() {
        if (instance == null) {
            synchronized (MGGameManager.class) {
                if (instance == null) {
                    instance = new MGGameManager();
                }
            }
        }
        return instance;
    }

    public boolean isRegisteredGame(@NotNull UUID gameID) {
        return registeredGames.containsKey(gameID) && registeredGames.get(gameID) != null;
    }

    @Nullable
    public MGGame getRegisteredGame(@NotNull UUID gameID) {
        return registeredGames.get(gameID);
    }

    public void registerGame(@NotNull MGGame game) {
        if (isRegisteredGame(game.getGameID())) return;
        registeredGames.put(game.getGameID(), game);
    }

    public void unregisterGame(@NotNull UUID gameID) {
        if (!isRegisteredGame(gameID)) return;
        registeredGames.remove(gameID);
    }

    public Map<UUID, MGGame> getRegisteredGames() {
        return Collections.unmodifiableMap(registeredGames);
    }
}
