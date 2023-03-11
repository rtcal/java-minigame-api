package com.rtcal.game.area.settings;

import org.jetbrains.annotations.NotNull;

public abstract class MGSettingsFlag<T> {

    private final String name;
    private final T defaultValue;
    private T value;

    public MGSettingsFlag(@NotNull String name, @NotNull T defaultValue, @NotNull T value) {
        this.name = name;
        this.value = value;
        this.defaultValue = defaultValue;
    }

    public final String getName() {
        return name;
    }

    public T getDefault() {
        return defaultValue;
    }

    public T getValue() {
        return value;
    }

    public void setValue(@NotNull T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{name=" + getName() + "value=" + getValue() + "}";
    }

}
