package com.rtcal.area.settings;

import com.rtcal.area.MGAreaSettings;

public class MGSimpleFlagTestSettings extends MGAreaSettings {

    public MGSimpleFlagTestSettings(){
        super(
                new MGSettingsFlagBoolean("pvp", true),
                new MGSettingsFlagInt("val", 1)
        );
    }

}
