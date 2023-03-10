package com.rtcal.game.arena;

import org.jetbrains.annotations.NotNull;

public class MGArena {

    private final String type;

    public MGArena(@NotNull String type) {
        this.type = type;

        MGArenaType.getInstance().register(this.type);
    }

    public String getType() {
        return type;
    }

}
