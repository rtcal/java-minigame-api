package com.rtcal.area.settings;

public abstract class MGSettingsFlag<T> {

    private final String name;
    private final T defaultValue;
    private T value;

    public MGSettingsFlag(String name, T defaultValue, T value) {
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

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "MGSettingsFlag{name=" + getName() + "value=" + getValue() + "}";
    }

}
