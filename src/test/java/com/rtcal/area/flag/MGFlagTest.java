package com.rtcal.area.flag;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MGFlagTest {

    @Test
    public void flagChecks() {
        MGSimpleFlagTestSettings settings = new MGSimpleFlagTestSettings();

        assertTrue(settings.hasFlag("pvp"));
        assertFalse(settings.hasFlag("unknown"));

        MGFlagBoolean mgFlagBoolean = (MGFlagBoolean) settings.getFlag("pvp");
        assertNotNull(mgFlagBoolean);
        assertTrue(mgFlagBoolean.getValue());

        MGFlagString mgFlagString = (MGFlagString) settings.getFlag("unknown");
        assertNull(mgFlagString);
    }

}
