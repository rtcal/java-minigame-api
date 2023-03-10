package com.rtcal.game.player;

import com.rtcal.game.team.MGTeam;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class MGPlayer {

    private final UUID playerID;
    private String name;

    private MGTeam team;

    public MGPlayer(@NotNull UUID playerID, @NotNull String name) {
        this.playerID = playerID;
        this.name = name;
    }

    public final UUID getPlayerID() {
        return playerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasTeam() {
        return getTeam() != null;
    }

    @Nullable
    public MGTeam getTeam() {
        return team;
    }

    public void setTeam(MGTeam team) {
        this.team = team;
    }

}
