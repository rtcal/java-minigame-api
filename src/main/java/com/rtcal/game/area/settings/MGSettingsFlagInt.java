package com.rtcal.game.area.settings;

import org.jetbrains.annotations.NotNull;

public class MGSettingsFlagInt extends MGSettingsFlag<Integer> {

    public MGSettingsFlagInt(@NotNull String name, int value) {
        super(name, value, value);
    }

}
