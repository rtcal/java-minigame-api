package com.rtcal.game.area;

import com.rtcal.game.area.settings.MGSettingsFlag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class MGAreaSettings {

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

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{flags=[" +
                String.join(",", flags.values().stream().map(MGSettingsFlag::toString).toList())
                + "]}";
    }

}
