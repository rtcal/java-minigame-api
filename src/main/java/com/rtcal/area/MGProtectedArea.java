package com.rtcal.area;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class MGProtectedArea extends MGArea {

    private final MGAreaSettings settings;
    private boolean settingsEnabled = true;

    public MGProtectedArea(@NotNull MGLocation loc1, @NotNull MGLocation loc2, @NotNull MGAreaSettings settings) {
        super(loc1, loc2);
        this.settings = settings;
    }

    public MGProtectedArea(@NotNull UUID uuid, @NotNull MGLocation loc1, @NotNull MGLocation loc2, @NotNull MGAreaSettings settings) {
        super(uuid, loc1, loc2);
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
