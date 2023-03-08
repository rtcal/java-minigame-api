package com.rtcal.map;

import com.rtcal.area.MGArea;
import com.rtcal.area.MGLocation;
import org.jetbrains.annotations.NotNull;

public class MGMap {

    private final String name;
    private final MGArea area;
    private final MGLocation minLocation, maxLocation;

    public MGMap(@NotNull final String name, @NotNull final MGArea area, @NotNull final MGLocation offset) {
        this.name = name;
        this.area = area;
        this.minLocation = offset;
        this.maxLocation = area.getSize().add(offset);
    }

    public final String getName() {
        return name;
    }

    public final MGArea getArea() {
        return area;
    }

    public final MGLocation getMinLocation() {
        return minLocation;
    }

    public final MGLocation getMaxLocation() {
        return maxLocation;
    }

    public final boolean isInside(MGLocation location) {
        return MGLocation.isInside(getMinLocation(), getMaxLocation(), location);
    }

}
