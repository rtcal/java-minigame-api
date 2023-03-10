package com.rtcal.game.area;

import org.jetbrains.annotations.NotNull;

public class MGArea {
    private final MGLocation minLocation, maxLocation;

    public MGArea(@NotNull MGLocation loc1, @NotNull MGLocation loc2) {
        this.minLocation = MGLocation.getMinimumLocation(loc1, loc2);
        this.maxLocation = MGLocation.getMaximumLocation(loc1, loc2);
    }

    public MGLocation getMinLocation() {
        return minLocation;
    }

    public MGLocation getMaxLocation() {
        return maxLocation;
    }

    public MGAreaSettings getSettings() {
        return null;
    }

    public boolean areSettingsEnabled() {
        return false;
    }

    public MGArea cloneMGAreaWithOffset(@NotNull MGLocation offset) {
        return new MGArea(getMinLocation().add(offset), getMaxLocation().add(offset));
    }

    /**
     * Check whether a location is inside an area
     *
     * @param location the location to be checked
     * @return whether the location is within the area
     */
    public boolean isInside(@NotNull MGLocation location) {
        return MGLocation.isInside(getMinLocation(), getMaxLocation(), location);
    }

    @Override
    public String toString() {
        return "MGArea{min=" + getMinLocation() + ",max=" + getMaxLocation() + "}";
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + getMinLocation().hashCode();
        hash = 31 * hash + getMaxLocation().hashCode();
        return hash;
    }

}
