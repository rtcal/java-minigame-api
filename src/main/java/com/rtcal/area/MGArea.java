package com.rtcal.area;

import com.rtcal.exceptions.MGDuplicateIDException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class MGArea {

    private final MGAreaManager areaManager;

    private final UUID uuid;
    private String name;
    private final int priority;
    private final MGLocation minLoc, maxLoc;

    private final Set<MGArea> childAreas = new HashSet<>();

    public MGArea(@NotNull String name, @NotNull MGLocation loc1, @NotNull MGLocation loc2, int priority, @NotNull MGAreaManager areaManager) throws MGDuplicateIDException {
        this(name, UUID.randomUUID(), loc1, loc2, priority, areaManager);
    }

    public MGArea(@NotNull String name, @NotNull UUID uuid, @NotNull MGLocation loc1, @NotNull MGLocation loc2, int priority, @NotNull MGAreaManager areaManager) throws MGDuplicateIDException {
        this.areaManager = areaManager;

        this.areaManager.areaNameCheck(name);
        this.areaManager.areaIDCheck(uuid);

        this.name = name;
        this.uuid = uuid;
        this.priority = priority;

        this.minLoc = MGLocation.getMinimumLocation(loc1, loc2);
        this.maxLoc = MGLocation.getMaximumLocation(loc1, loc2);

        this.areaManager.registerArea(this);
    }

    @NotNull
    public MGAreaManager getAreaManager() {
        return this.areaManager;
    }

    @NotNull
    public final UUID getID() {
        return uuid;
    }

    @NotNull
    public final String getName() {
        return name;
    }

    public final int getPriority() {
        return priority;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    @NotNull
    public final MGLocation getMinLoc() {
        return minLoc;
    }

    @NotNull
    public final MGLocation getMaxLoc() {
        return maxLoc;
    }

    public boolean hasChildAreas() {
        return childAreas.size() > 0;
    }

    public Set<MGArea> getChildAreas() {
        return childAreas;
    }

    public MGAreaSettings getSettings() {
        return null;
    }

    public synchronized boolean addChildArea(MGArea childArea) throws IllegalArgumentException {
        if (childArea == null || childAreas.contains(childArea)) return false;

        if (!isInside(childArea.getMinLoc()) || !isInside(childArea.getMaxLoc()))
            throw new IllegalArgumentException("MGArea child is not inside parent MGArea");

        if (childArea.getPriority() <= getPriority())
            throw new IllegalArgumentException("MGArea child priority (" + childArea.getPriority() + ") must be greater than parent priority");

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
    public MGArea getActiveArea(MGLocation location) {
        if (!isInside(location)) return null;
        if (hasChildAreas()) return this;

        MGArea activeArea = this;
        MGArea childActiveArea;

        for (MGArea childArea : getChildAreas()) {
//            if (!(childArea instanceof MGProtectedArea protectedArea)) continue;

            childActiveArea = childArea.getActiveArea(location);

            if (childActiveArea != null && childActiveArea.getSettings() != null && childActiveArea.getPriority() > activeArea.getPriority()) {
                activeArea = childActiveArea;
            }
        }

        return activeArea;
    }

    @Override
    public String toString() {
        String childAreaString = "";

        if (hasChildAreas()) {
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
