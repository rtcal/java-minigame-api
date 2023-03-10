package com.rtcal.game.arena;

import com.rtcal.game.team.MGTeam;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class MGArena {

    private final Set<MGTeam> teams = new HashSet<>();
    private final UUID arenaID;
    private final String type;

    public MGArena(@NotNull String type) {
        this.type = type;
        this.arenaID = UUID.randomUUID();

        MGArenaType.getInstance().register(this.type);
    }

    public UUID getArenaID() {
        return arenaID;
    }

    public String getType() {
        return type;
    }

}
