package com.rtcal.area;

import com.rtcal.area.exceptions.MGDuplicateIDException;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class MGProtectedArea extends MGArea {

    private final MGAreaSettings settings;
    private boolean settingsEnabled = true;

    public MGProtectedArea(@NotNull String name, @NotNull MGLocation loc1, @NotNull MGLocation loc2, @NotNull MGAreaSettings settings, int priority, @NotNull MGAreaManager areaManager) throws MGDuplicateIDException {
        super(name, loc1, loc2, priority, areaManager);
        this.settings = settings;
    }

    public MGProtectedArea(@NotNull String name, @NotNull UUID uuid, @NotNull MGLocation loc1, @NotNull MGLocation loc2, @NotNull MGAreaSettings settings, int priority, @NotNull MGAreaManager areaManager) throws MGDuplicateIDException {
        super(name, uuid, loc1, loc2, priority, areaManager);
        this.settings = settings;
    }

    @Override
    public MGAreaSettings getSettings() {
        return settings;
    }

    public boolean areSettingsEnabled() {
        return settingsEnabled;
    }

    public synchronized void toggleSettings(boolean enabled) {
        this.settingsEnabled = enabled;
    }

}
