package com.rtcal.game;

import com.rtcal.exceptions.MGDuplicateException;
import com.rtcal.game.team.MGTeam;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class MGTeamManager {

    private static volatile MGTeamManager instance;

    private final Map<String, MGTeam> teams = new ConcurrentHashMap<>();

    private MGTeamManager() {

    }

    public static MGTeamManager getInstance() {
        if (instance == null) {
            synchronized (MGTeamManager.class) {
                if (instance == null) {
                    instance = new MGTeamManager();
                }
            }
        }
        return instance;
    }

    public boolean isRegisteredTeam(@NotNull String name) {
        return teams.containsKey(name) && teams.get(name) != null;
    }

    public void registerTeam(@NotNull MGTeam team) throws MGDuplicateException {
        if (isRegisteredTeam(team.getName())) throw new MGDuplicateException("MGTeam with name '" + team.getName() + "' already exists");
        teams.put(team.getName(), team);
    }

    public void unregisterTeam(@NotNull String name) throws NullPointerException {
        if (!isRegisteredTeam(name)) throw new NullPointerException("MGTeam '" + name + "' is not a registered team");
        teams.remove(name);
    }

    public Map<String, MGTeam> getTeams() {
        return teams;
    }

}
