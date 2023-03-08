package com.rtcal.area;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MGLocationTest {

    @Test
    public void locationHashCheck() {
        MGLocation location1 = new MGLocation(1, 2, 3);
        MGLocation location2 = new MGLocation(2, 1, 3); // x and y swapped

        int locationHash1 = location1.hashCode();
        int locationHash2 = location2.hashCode();

        assertNotEquals(locationHash1, locationHash2);
    }

}
