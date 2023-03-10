package com.rtcal.game.team;

import com.rtcal.exceptions.MGDuplicateException;
import com.rtcal.exceptions.MGPlayerBusyException;
import com.rtcal.game.player.MGPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;


public class MGTeam {

    private final Set<MGPlayer> players = new HashSet<>();
    private final String name;
    private final int capacity;

    public MGTeam(@NotNull String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean hasCapacity() {
        return players.size() < getCapacity();
    }

    public boolean containsPlayer(@NotNull MGPlayer player) {
        return players.contains(player);
    }

    public void addPlayer(@NotNull MGPlayer player) throws MGDuplicateException, MGPlayerBusyException {
        if (containsPlayer(player)) throw new MGDuplicateException("MGPlayer '" + player.getName() + "' already part of MGTeam '" + getName() + "'");
        if (player.hasTeam()) throw new MGPlayerBusyException("MGPlayer '" + player.getName() + "' already has a team");
        players.add(player);
        player.setTeam(this);
    }

    public void removePlayer(@NotNull MGPlayer player) throws NullPointerException {
        if (!containsPlayer(player)) throw new NullPointerException("MGPlayer '" + player.getName() + "' is not part of MGTeam '" + getName() + "'");
        players.remove(player);
        player.setTeam(null);
    }

    public Set<MGPlayer> getPlayers() {
        return players;
    }

}
