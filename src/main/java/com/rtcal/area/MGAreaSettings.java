package com.rtcal.area;

import com.rtcal.area.flag.MGFlag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public abstract class MGAreaSettings {

    private final Map<String, MGFlag<?>> flags = new HashMap<>();

    public MGAreaSettings(MGFlag<?>... flags) {
        for (MGFlag<?> flag : flags) {
            this.flags.put(flag.getName(), flag);
        }
    }

    public boolean hasFlag(@NotNull String name) {
        return flags.containsKey(name) && flags.get(name) != null;
    }

    @Nullable
    public MGFlag<?> getFlag(@NotNull String name) {
        return flags.get(name);
    }

    public void setFlag(@NotNull MGFlag<?> flag) {
        flags.put(flag.getName(), flag);
    }

}
