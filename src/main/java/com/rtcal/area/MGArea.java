package com.rtcal.area;

import java.util.UUID;

public class MGArea {

    private final UUID uuid;
    private final MGAreaSettings settings;
    private final MGLocation minLoc, maxLoc;

    private boolean settingsEnabled = true;

    public MGArea(MGLocation loc1, MGLocation loc2, MGAreaSettings settings) {
        this(UUID.randomUUID(), loc1, loc2, settings);
    }

    public MGArea(UUID uuid, MGLocation loc1, MGLocation loc2, MGAreaSettings settings) {
        this.uuid = uuid;
        this.settings = settings;

        this.minLoc = MGLocation.getMinimumLocation(loc1, loc2);
        this.maxLoc = MGLocation.getMaximumLocation(loc1, loc2);
    }

    public final UUID getID() {
        return uuid;
    }

    public final MGAreaSettings getSettings() {
        return settings;
    }

    public boolean areSettingsEnabled() {
        return settingsEnabled;
    }

    public void toggleSettings(boolean enabled) {
        this.settingsEnabled = enabled;
    }

    public final MGLocation getMinLoc() {
        return minLoc;
    }

    public final MGLocation getMaxLoc() {
        return maxLoc;
    }

    /**
     * Check whether a location is inside an area
     *
     * @param location the location to be checked
     * @return whether the location is within the area
     */
    public boolean isInside(MGLocation location) {
        int x = location.getX();
        int y = location.getY();
        int z = location.getZ();

        boolean insideX = (x >= minLoc.getX()) && (x <= maxLoc.getX());
        boolean insideY = (y >= minLoc.getY()) && (y <= maxLoc.getY());
        boolean insideZ = (z >= minLoc.getZ()) && (z <= maxLoc.getZ());

        return insideX && insideY && insideZ;
    }

    @Override
    public String toString() {
        return "MGArea{uuid=" + getID().toString() + ",minLocation=" + getMinLoc().toString() + ",maxLocation=" + getMaxLoc().toString() + "}";
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + getMinLoc().hashCode();
        hash = 31 * hash + getMaxLoc().hashCode();
        hash = 31 * hash + getID().hashCode();
        return hash;
    }

}
