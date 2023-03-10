package com.rtcal.game.map;

import com.rtcal.game.area.MGArea;
import com.rtcal.game.area.MGLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * MGMap links a map name to a playable MGArea
 */
public class MGMap {

    private final String name;
    private final MGArea area;

    public MGMap(@NotNull final String name, @NotNull final MGArea area) {
        this.name = name;
        this.area = area;
    }

    public MGMap(@NotNull MGMap original, @Nullable MGLocation mapOffset) {
        this.name = original.getName();

        if (mapOffset == null) {
            this.area = original.area;
        } else {
            this.area = original.area.cloneMGAreaWithOffset(mapOffset);
        }
    }

    public final String getName() {
        return name;
    }

    public final MGArea getArea() {
        return area;
    }

    @Override
    public String toString() {
        return "MGMap{name=" + getName() + "area=" + getArea() + "}";
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + getName().hashCode();
        hash = 31 * hash + getArea().hashCode();
        return hash;
    }
}
