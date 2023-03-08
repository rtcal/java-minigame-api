package com.rtcal.area.flag;

public abstract class MGFlag<T> {

    private final String name;
    private final T defaultValue;
    private T value;

    public MGFlag(String name, T defaultValue, T value) {
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

}
