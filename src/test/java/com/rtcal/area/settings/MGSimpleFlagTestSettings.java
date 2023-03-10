package com.rtcal.area.settings;

import com.rtcal.game.area.MGAreaSettings;
import com.rtcal.game.area.settings.MGSettingsFlagBoolean;
import com.rtcal.game.area.settings.MGSettingsFlagInt;

public class MGSimpleFlagTestSettings extends MGAreaSettings {

    public MGSimpleFlagTestSettings(){
        super(
                new MGSettingsFlagBoolean("pvp", true),
                new MGSettingsFlagInt("val", 1)
        );
    }

}
