package com.rtcal.game.arena;

import com.rtcal.game.enums.MGPlayerResponse;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


public class MGTeam {

    private final Set<MGPlayer> players = ConcurrentHashMap.newKeySet();

    private final UUID teamID;
    private final String name;
    private final int capacity;

    public MGTeam(@NotNull String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.teamID = UUID.randomUUID();
    }

    public final UUID getTeamID() {
        return teamID;
    }

    public final String getName() {
        return name;
    }

    public final int getCapacity() {
        return capacity;
    }

    public boolean hasCapacity() {
        return players.size() < getCapacity();
    }

    public boolean containsPlayer(@NotNull MGPlayer player) {
        return players.contains(player);
    }

    // TODO: get the arena the team is a member of and and register that the player is in that arena
    public MGPlayerResponse addPlayer(@NotNull MGPlayer player) {
        if (containsPlayer(player)) return MGPlayerResponse.PLAYER_DUPLICATE;
        if (player.hasTeam()) return MGPlayerResponse.PLAYER_HAS_TEAM;

        players.add(player);
        player.setTeam(this);

        return MGPlayerResponse.SUCCESS;
    }

    public MGPlayerResponse removePlayer(@NotNull MGPlayer player) {
        if (!containsPlayer(player)) return MGPlayerResponse.PLAYER_NOT_FOUND;

        players.remove(player);
        player.setTeam(null);

        return MGPlayerResponse.SUCCESS;
    }

    public Set<MGPlayer> getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{name=" + getName() +
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

}
