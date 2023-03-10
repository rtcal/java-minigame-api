package com.rtcal.game;

import com.rtcal.exceptions.MGDuplicateException;
import com.rtcal.game.team.MGTeam;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class MGTeamManager {

    private static volatile MGTeamManager instance;

    private final Map<String, MGTeam> teams = new HashMap<>();
    private final Lock lock = new ReentrantLock();

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
        lock.lock();
        try {
            return teams.containsKey(name) && teams.get(name) != null;
        } finally {
            lock.unlock();
        }
    }

    public void registerTeam(@NotNull MGTeam team) throws MGDuplicateException {
        lock.lock();
        try {
            if (isRegisteredTeam(team.getName())) throw new MGDuplicateException("MGTeam with name '" + team.getName() + "' already exists");
            teams.put(team.getName(), team);
        } finally {
            lock.unlock();
        }
    }

    public void unregisterTeam(@NotNull String name) throws NullPointerException {
        lock.lock();
        try {
            if (!isRegisteredTeam(name)) throw new NullPointerException("MGTeam '" + name + "' is not a registered team");
            teams.remove(name);
        } finally {
            lock.unlock();
        }
    }

}
