package com.rtcal.game.arena;

import com.rtcal.exceptions.MGDuplicateException;
import com.rtcal.game.MGArenaManager;
import com.rtcal.game.MGGameModeManager;
import com.rtcal.game.area.MGLocation;
import com.rtcal.game.area.map.MGMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * MGArena creates an arena for a game mode type on an MGMap
 * MGArena name is created by MGMap.getName()_Type e.g. office_ffa (office is the map, ffa is the game mode type)
 */
public abstract class MGArena {

    private final Map<String, MGTeam> registeredTeams = new ConcurrentHashMap<>();

    private final UUID arenaID;
    private final String type;
    private final String name;
    private final MGMap map;

    /**
     * Used for creating a unique instance of an arena
     *
     * @param type the game mode type e.g. ffa, tdm, conquest, capture the flag...
     * @param map  the map to be used for this arena
     * @throws MGDuplicateException If a unique instance of a map_type exists i.e. cannot create two office_ffa instances
     */
    public MGArena(@NotNull String type, @NotNull MGMap map) throws MGDuplicateException {
        this.type = type;
        this.name = type + "_" + map.getName();
        this.map = map;
        this.arenaID = UUID.randomUUID();

        MGGameModeManager.getInstance().register(this.type);
        MGArenaManager.getInstance().registerArena(this);
    }

    /**
     * Used for creating a new instance of an arena
     *
     * @param original  the original arena to base the new arena on
     * @param mapOffset if implementations support same-world arenas an offset will allow the arena to be spawned in an unused location
     */
    public MGArena(@NotNull MGArena original, @Nullable MGLocation mapOffset) {
        this.type = original.getType();
        this.name = original.getName();
        this.map = original.getMap().cloneMGMapWithOffset(mapOffset);
        this.arenaID = UUID.randomUUID();
    }

    public abstract MGArena cloneArena(@Nullable MGLocation mapOffset);

    public UUID getArenaID() {
        return arenaID;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public MGMap getMap() {
        return map;
    }

    public boolean isRegisteredTeam(@NotNull String name) {
        name = name.toLowerCase();
        return registeredTeams.containsKey(name) && registeredTeams.get(name) != null;
    }

    public void registerTeam(@NotNull MGTeam team) throws MGDuplicateException {
        String name = team.getName().toLowerCase();
        if (isRegisteredTeam(name)) throw new MGDuplicateException("MGTeam with name '" + name + "' is already a member of MGArena '" + getName() + "'");
        registeredTeams.put(name, team);
    }

    public void unregisterTeam(@NotNull String name) {
        final String lowerCaseName = name.toLowerCase();
        registeredTeams.remove(lowerCaseName);
    }

    public Set<String> getTeamNames() {
        return Collections.unmodifiableSet(registeredTeams.keySet());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{uuid=" + getArenaID() + ",name=" + getName() + ",type=" + getType() + ",map=" + getMap() + "}";
    }

}
