package com.rtcal.game.area.settings;

import org.jetbrains.annotations.NotNull;

public class MGSettingsFlagBoolean extends MGSettingsFlag<Boolean> {

    public MGSettingsFlagBoolean(@NotNull String name, boolean value) {
        super(name, value, value);
    }

}
