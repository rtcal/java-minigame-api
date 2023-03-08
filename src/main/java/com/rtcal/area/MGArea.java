package com.rtcal.area;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class MGArea {

    private final UUID uuid;
    private final MGAreaSettings settings;
    private final MGLocation minLoc, maxLoc;

    private boolean settingsEnabled = true;

    private final Set<MGArea> childAreas = new HashSet<>();

    public MGArea(@NotNull MGLocation loc1, @NotNull MGLocation loc2, @NotNull MGAreaSettings settings) {
        this(UUID.randomUUID(), loc1, loc2, settings);
    }

    public MGArea(@NotNull UUID uuid, @NotNull MGLocation loc1, @NotNull MGLocation loc2, @NotNull MGAreaSettings settings) {
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

    public synchronized void toggleSettings(boolean enabled) {
        this.settingsEnabled = enabled;
    }

    public final MGLocation getMinLoc() {
        return minLoc;
    }

    public final MGLocation getMaxLoc() {
        return maxLoc;
    }

    public synchronized boolean addChildArea(MGArea childArea) throws IllegalArgumentException {
        if (childArea == null || childAreas.contains(childArea)) return false;

        if (!isInside(childArea.getMinLoc()) || !isInside(childArea.getMaxLoc()))
            throw new IllegalArgumentException("Child MGArea is not inside parent MGArea");

        return childAreas.add(childArea);
    }

    public synchronized boolean removeChildArea(MGArea childArea) {
        return childArea != null && childAreas.remove(childArea);
    }

    /**
     * Check whether a location is inside an area
     *
     * @param location the location to be checked
     * @return whether the location is within the area
     */
    public boolean isInside(MGLocation location) {
        if (location == null) return false;

        int x = location.getX();
        int y = location.getY();
        int z = location.getZ();

        boolean insideX = (x >= minLoc.getX()) && (x <= maxLoc.getX());
        boolean insideY = (y >= minLoc.getY()) && (y <= maxLoc.getY());
        boolean insideZ = (z >= minLoc.getZ()) && (z <= maxLoc.getZ());

        return insideX && insideY && insideZ;
    }

    @Nullable
    public MGAreaSettings getActiveSettings(MGLocation location) {
        if (!isInside(location)) return null;
        if (childAreas.size() == 0) return getSettings();

        MGAreaSettings activeSettings = getSettings();
        MGAreaSettings childActiveSettings;

        for (MGArea childArea : childAreas) {
            childActiveSettings = childArea.getActiveSettings(location);

            if (childActiveSettings != null) {
                activeSettings = childActiveSettings;
            }
        }

        return activeSettings;
    }

    @Override
    public String toString() {
        String childAreaString = "";

        if (childAreas.size() > 0) {
            List<String> childAreaStrings = childAreas.stream().map(MGArea::toString).toList();
            childAreaString = String.join(",", childAreaStrings);
        }

        return "MGArea{uuid=" + getID().toString() +
                ",minLocation=" + getMinLoc().toString() +
                ",maxLocation=" + getMaxLoc().toString() +
                ",children=[" + childAreaString + "]" +
                "}";
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
