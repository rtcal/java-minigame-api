package com.rtcal.area;

import org.jetbrains.annotations.NotNull;

public class MGProtectedArea extends MGArea {

    private final MGAreaSettings settings;
    private boolean settingsEnabled = true;

    public MGProtectedArea(@NotNull MGLocation loc1, @NotNull MGLocation loc2, @NotNull MGAreaSettings settings) {
        super(loc1, loc2);
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

    @Override
    public MGProtectedArea cloneMGAreaWithOffset(MGLocation offset) {
        MGProtectedArea area = new MGProtectedArea(getMinLocation().add(offset), getMaxLocation().add(offset), settings);
        area.toggleSettings(areSettingsEnabled());
        return area;
    }

    @Override
    public String toString() {
        return "MGProtectedArea{min=" + getMinLocation() + ",max=" + getMaxLocation() + "}";
    }

}
