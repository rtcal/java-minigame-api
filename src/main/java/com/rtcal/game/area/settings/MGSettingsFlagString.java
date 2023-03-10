package com.rtcal.game.area.settings;

import org.jetbrains.annotations.NotNull;

public class MGSettingsFlagString extends MGSettingsFlag<String> {

    public MGSettingsFlagString(@NotNull String name, String value) {
        super(name, value, value);
    }

}
