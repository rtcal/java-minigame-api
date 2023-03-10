package com.rtcal.game.map;

import com.rtcal.game.area.MGArea;
import org.jetbrains.annotations.NotNull;

public class MGMap {

    private final String name;
    private final MGArea area;

    public MGMap(@NotNull final String name, @NotNull final MGArea area) {
        this.name = name;
        this.area = area;
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
        return hash;
    }
}
