package com.rtcal.area;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class MGArea {

    private final UUID uuid;
    private final MGLocation size;

    public MGArea(@NotNull MGLocation size) {
        this(UUID.randomUUID(), size);
    }

    public MGArea(@NotNull UUID uuid, @NotNull MGLocation size) {
        this.uuid = uuid;
        this.size = MGLocation.abs(size);
    }

    @NotNull
    public final UUID getID() {
        return uuid;
    }

    @NotNull
    public final MGLocation getSize() {
        return size;
    }

    public MGAreaSettings getSettings() {
        return null;
    }

    public boolean areSettingsEnabled() {
        return false;
    }

    /**
     * Check whether a location is inside an area
     *
     * @param location the location to be checked
     * @return whether the location is within the area
     */
    public boolean isInside(MGLocation location) {
        return MGLocation.isInside(MGLocation.ZERO, size, location);
    }

    @Override
    public String toString() {
        return "MGArea{uuid=" + getID() +
                ",size=" + getSize() +
                "}";
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + getSize().hashCode();
        hash = 31 * hash + getID().hashCode();
        return hash;
    }

}
