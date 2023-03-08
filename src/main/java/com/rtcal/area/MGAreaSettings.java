package com.rtcal.area;

import com.rtcal.area.settings.MGSettingsFlag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public abstract class MGAreaSettings {

    private final Map<String, MGSettingsFlag<?>> flags = new HashMap<>();

    public MGAreaSettings(MGSettingsFlag<?>... flags) {
        for (MGSettingsFlag<?> flag : flags) {
            this.flags.put(flag.getName(), flag);
        }
    }

    public boolean hasFlag(@NotNull String name) {
        return flags.containsKey(name) && flags.get(name) != null;
    }

    @Nullable
    public MGSettingsFlag<?> getFlag(@NotNull String name) {
        return flags.get(name);
    }

    public void setFlag(@NotNull MGSettingsFlag<?> flag) {
        flags.put(flag.getName(), flag);
    }

}
