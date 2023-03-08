package com.rtcal.area.settings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MGSettingsFlagTest {

    @Test
    public void flagChecks() {
        MGSimpleFlagTestSettings settings = new MGSimpleFlagTestSettings();

        assertTrue(settings.hasFlag("pvp"));
        assertFalse(settings.hasFlag("unknown"));

        MGSettingsFlagBoolean mgFlagBoolean = (MGSettingsFlagBoolean) settings.getFlag("pvp");
        assertNotNull(mgFlagBoolean);
        assertTrue(mgFlagBoolean.getValue());

        MGSettingsFlagString mgFlagString = (MGSettingsFlagString) settings.getFlag("unknown");
        assertNull(mgFlagString);
    }

}
