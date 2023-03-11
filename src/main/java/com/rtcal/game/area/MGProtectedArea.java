package com.rtcal.game.area;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MGProtectedArea extends MGArea {

    private final MGAreaSettings settings;
    private boolean settingsEnabled = true;

    public MGProtectedArea(@NotNull MGLocation loc1, @NotNull MGLocation loc2, @NotNull MGAreaSettings settings) {
        super(loc1, loc2);
        this.settings = settings;
    }

    public MGAreaSettings getSettings() {
        return settings;
    }

    public boolean areSettingsEnabled() {
        return settingsEnabled;
    }

    public synchronized void toggleSettings(boolean enabled) {
        this.settingsEnabled = enabled;
    }

    @Override
    public MGProtectedArea cloneMGAreaWithOffset(@Nullable MGLocation offset) {
        return new MGProtectedArea(getMinLocation().add(offset), getMaxLocation().add(offset), getSettings());
    }

}
