package com.rtcal.game.team;

import com.rtcal.exceptions.MGDuplicateException;
import com.rtcal.exceptions.MGPlayerBusyException;
import com.rtcal.game.MGTeamManager;
import com.rtcal.game.player.MGPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


public class MGTeam {

    private final Set<MGPlayer> players = new HashSet<>();

    private final UUID teamID;
    private final String name;
    private final int capacity;

    public MGTeam(@NotNull String name, int capacity) throws MGDuplicateException {
        this.name = name;
        this.capacity = capacity;
        this.teamID = UUID.randomUUID();

        MGTeamManager.getInstance().registerTeam(this);
    }

    public UUID getTeamID() {
        return teamID;
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

    @Override
    public String toString() {
        return "MGTeam{name=" + getName() +
                ",players=[" + String.join(",", players.stream().map(MGPlayer::toString).toList()) + "]"
                + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        final MGTeam other = (MGTeam) o;

        if (!getTeamID().equals(other.getTeamID())) return false;
        return getName().equals(other.getName());
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + getTeamID().hashCode();
        hash = 31 * hash + getName().hashCode();
        return hash;
    }

}
