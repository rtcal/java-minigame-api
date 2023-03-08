package com.rtcal.area;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class MGProtectedArea extends MGArea {

    private final MGAreaSettings settings;
    private boolean settingsEnabled = true;

    public MGProtectedArea(@NotNull MGLocation size, @NotNull MGAreaSettings settings) {
        super(size);
        this.settings = settings;
    }

    public MGProtectedArea(@NotNull UUID uuid, @NotNull MGLocation size, @NotNull MGAreaSettings settings) {
        super(uuid, size);
        this.settings = settings;
    }

    @Override
    public MGAreaSettings getSettings() {
        return settings;
    }

    @Override
    public boolean areSettingsEnabled() {
        return settingsEnabled;
    }

    public synchronized void toggleSettings(boolean enabled) {
        this.settingsEnabled = enabled;
    }

}
