package com.rtcal.game;

import com.rtcal.exceptions.MGDuplicateException;
import com.rtcal.game.arena.MGArena;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * MGArenaManager holds types of arenas
 * an MGArena instance will only ever hold a single instance for a combination of MGMap and MGGameMode
 * meaning there can only be one instance of office_ffa registered here
 */
public final class MGArenaManager {

    private static volatile MGArenaManager instance;

    private final Map<String, MGArena> registeredArenas = new ConcurrentHashMap<>();
    private final Map<String, Set<String>> registeredArenasByTypeMapping = new ConcurrentHashMap<>();
    private final Map<String, Set<String>> registeredArenasByMapNameMapping = new ConcurrentHashMap<>();


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

    @NotNull
    public MGArena getRegisteredArenaByName(@NotNull String name) {
        name = name.toLowerCase();
        if (!isRegisteredArena(name)) throw new NullPointerException("MGArena '" + name + "' is not a registered arena");
        return registeredArenas.get(name);
    }

    public boolean isRegisteredArena(@NotNull String name) {
        name = name.toLowerCase();
        return registeredArenas.containsKey(name) && registeredArenas.get(name) != null;
    }

    public void registerArena(@NotNull MGArena arena) throws MGDuplicateException {
        String name = arena.getName().toLowerCase();
        if (isRegisteredArena(name)) throw new MGDuplicateException("MGArena with name '" + name + "' already exists");
        registeredArenas.put(name, arena);
        registeredArenasByTypeMapping.computeIfAbsent(arena.getType(), k -> ConcurrentHashMap.newKeySet()).add(name);
        registeredArenasByMapNameMapping.computeIfAbsent(arena.getMap().getName(), k -> ConcurrentHashMap.newKeySet()).add(name);
    }

    // TODO: Ensure arena instance isn't running
    public void unregisterArena(@NotNull String name) {
        final String lowerCaseName = name.toLowerCase();

        registeredArenasByTypeMapping.computeIfPresent(registeredArenas.get(name).getType(), (type, arenasOfType) -> {
            arenasOfType.remove(lowerCaseName);
            return arenasOfType;
        });

        registeredArenasByMapNameMapping.computeIfPresent(registeredArenas.get(name).getMap().getName(), (map, arenasOfType) -> {
            arenasOfType.remove(lowerCaseName);
            return arenasOfType;
        });

        registeredArenas.remove(lowerCaseName);
    }

    public Map<String, MGArena> getRegisteredArenas() {
        return Collections.unmodifiableMap(registeredArenas);
    }

    public Collection<MGArena> getRegisteredArenasByType(@NotNull String type) {
        type = type.toLowerCase();
        if (!MGGameModeManager.getInstance().isType(type)) throw new NullPointerException("MGAreaType '" + type + "' does not exist");

        List<MGArena> arenas = new ArrayList<>();

        for (String name : registeredArenasByTypeMapping.get(type)) {
            try {
                arenas.add(getRegisteredArenaByName(name));
            } catch (NullPointerException e) {
                unregisterArena(name); // The arena hasn't been registered correctly, unregister it completely to avoid issues
            }
        }

        return Collections.unmodifiableCollection(arenas);
    }

    public Collection<MGArena> getRegisteredArenasByMapName(@NotNull String map) {
        map = map.toLowerCase();

        List<MGArena> arenas = new ArrayList<>();

        for (String name : registeredArenasByMapNameMapping.get(map)) {
            try {
                arenas.add(getRegisteredArenaByName(name));
            } catch (NullPointerException e) {
                unregisterArena(name); // The arena hasn't been registered correctly, unregister it completely to avoid issues
            }
        }

        return Collections.unmodifiableCollection(arenas);
    }

}