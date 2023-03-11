package com.rtcal.game.arena;

import com.rtcal.game.MGGameManager;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public abstract class MGGame {

    private final MGArena arena;

    public MGGame(@NotNull MGArena arena) {
        this.arena = arena;

        MGGameManager.getInstance().registerGame(this);
    }

    public UUID getGameID() {
        return arena.getArenaID();
    }

    public MGArena getArena() {
        return arena;
    }

}
