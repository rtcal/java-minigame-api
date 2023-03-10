package com.rtcal.area;


import com.rtcal.game.area.MGArea;
import com.rtcal.game.area.MGLocation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MGAreaTest {

    @Test
    public void testIsInside() {
        MGArea area = new MGArea(MGLocation.ZERO, new MGLocation(20, 20, 20));

        assertTrue(area.isInside(new MGLocation(0, 0, 0)));
        assertTrue(area.isInside(new MGLocation(20, 20, 20)));
        assertTrue(area.isInside(new MGLocation(15, 15, 20)));

        assertFalse(area.isInside(new MGLocation(-1, 0, 0)));
        assertFalse(area.isInside(new MGLocation(21, 5, 5)));
        assertFalse(area.isInside(new MGLocation(5, 25, 5)));
        assertFalse(area.isInside(new MGLocation(2, 5, 25)));
    }

}
