package com.rtcal.game.player;

import com.rtcal.game.team.MGTeam;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MGPlayer {

    private final Lock teamLock = new ReentrantLock();
    private final Lock nameLock = new ReentrantLock();

    private final UUID playerID;
    private String name;

    private volatile MGTeam team;

    public MGPlayer(@NotNull UUID playerID, @NotNull String name) {
        this.playerID = playerID;
        this.name = name;
    }

    public final UUID getPlayerID() {
        return playerID;
    }

    public String getName() {
        nameLock.lock();
        try {
            return name;
        } finally {
            nameLock.unlock();
        }
    }

    public void setName(String name) {
        nameLock.lock();
        try {
            this.name = name;
        } finally {
            nameLock.unlock();
        }
    }

    public boolean hasTeam() {
        teamLock.lock();
        try {
            return getTeam() != null;
        } finally {
            teamLock.unlock();
        }
    }

    @Nullable
    public MGTeam getTeam() {
        teamLock.lock();
        try {
            return team;
        } finally {
            teamLock.unlock();
        }
    }

    public void setTeam(MGTeam team) {
        teamLock.lock();
        try {
            this.team = team;
        } finally {
            teamLock.unlock();
        }
    }

    @Override
    public String toString() {
        return "MGPlayer{uuid=" + getPlayerID() + ",name=" + getName() + "}";
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + getPlayerID().hashCode();
        hash = 31 * hash + getName().hashCode();
        return hash;
    }

}
