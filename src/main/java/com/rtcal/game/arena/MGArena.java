package com.rtcal.game.arena;

import com.rtcal.exceptions.MGDuplicateException;
import com.rtcal.game.MGArenaManager;
import com.rtcal.game.MGGameModeManager;
import com.rtcal.game.area.MGLocation;
import com.rtcal.game.map.MGMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * MGArena creates an arena for a game mode type on an MGMap
 * MGArena name is created by MGMap.getName()_Type e.g. office_ffa (office is the map, ffa is the game mode type)
 */
public class MGArena {
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
        this.name = map.getName() + "_" + type;
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
        this.map = new MGMap(original.getMap(), mapOffset);
        this.arenaID = UUID.randomUUID();
    }

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

}
