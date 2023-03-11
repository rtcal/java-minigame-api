package com.rtcal.implementations.arenas;

import com.rtcal.exceptions.MGDuplicateException;
import com.rtcal.game.area.MGLocation;
import com.rtcal.game.arena.MGArena;
import com.rtcal.game.map.MGMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class MGTeamDeathmatchArena extends MGArena {

    public MGTeamDeathmatchArena(@NotNull MGMap map) throws MGDuplicateException {
        super("tdm", map);
    }

    public MGTeamDeathmatchArena(@NotNull MGTeamDeathmatchArena original, @Nullable MGLocation mapOffset) {
        super(original, mapOffset);
    }

    @Override
    public MGTeamDeathmatchArena cloneArena(@Nullable MGLocation mapOffset) {
        return new MGTeamDeathmatchArena(this, mapOffset);
    }
}
