package com.rtcal.area.flag;

import com.rtcal.area.MGAreaSettings;

public class MGSimpleFlagTestSettings extends MGAreaSettings {

    public MGSimpleFlagTestSettings(){
        super(
                new MGFlagBoolean("pvp", true),
                new MGFlagInt("val", 1)
        );
    }

}
