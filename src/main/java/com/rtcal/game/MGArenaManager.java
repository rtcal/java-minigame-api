package com.rtcal.game;

import com.rtcal.exceptions.MGDuplicateException;
import com.rtcal.game.arena.MGArena;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * MGArenaManager holds types of arenas
 * an MGArena instance will only ever hold a single instance for a combination of MGMap and MGGameMode
 * meaning there can only be one instance of office_ffa registered here
 */
public final class MGArenaManager {

    private static volatile MGArenaManager instance;

    private final Map<String, MGArena> arenas = new ConcurrentHashMap<>();
    private final Map<String, Set<String>> arenasByType = new ConcurrentHashMap<>();
    private final Map<String, Set<String>> arenasByMap = new ConcurrentHashMap<>();


    private MGArenaManager() {

    }

    public static MGArenaManager getInstance() {
        if (instance == null) {
            synchronized (MGArenaManager.class) {
                if (instance == null) {
                    instance = new MGArenaManager();
                }
            }
        }
        return instance;
    }

    public boolean isRegisteredArena(@NotNull String name) {
        return arenas.containsKey(name) && arenas.get(name) != null;
    }

    public void registerArena(@NotNull MGArena arena) throws MGDuplicateException {
        if (isRegisteredArena(arena.getName())) throw new MGDuplicateException("MGArena with name '" + arena.getName() + "' already exists");
        arenas.put(arena.getName(), arena);
        arenasByType.computeIfAbsent(arena.getType(), k -> ConcurrentHashMap.newKeySet()).add(arena.getName());
        arenasByMap.computeIfAbsent(arena.getMap().getName(), k -> ConcurrentHashMap.newKeySet()).add(arena.getName());
    }

    // TODO: Ensure arena instance isn't running
    public void unregisterArena(@NotNull String name) throws NullPointerException {
        if (!isRegisteredArena(name)) throw new NullPointerException("MGArena '" + name + "' is not a registered arena");

        arenasByType.computeIfPresent(arenas.get(name).getType(), (type, arenasOfType) -> {
            arenasOfType.remove(name);
            return arenasOfType;
        });

        arenasByMap.computeIfPresent(arenas.get(name).getMap().getName(), (map, arenasOfType) -> {
            arenasOfType.remove(name);
            return arenasOfType;
        });

        arenas.remove(name);
    }

    public Map<String, MGArena> getArenas() {
        return arenas;
    }

}