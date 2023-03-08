package com.rtcal.area;


import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class MGAreaTest {

    @Test
    public void testIsInside() {
        MGArea area = new MGArea(
                new MGLocation(0, 0, 0),
                new MGLocation(20, 20, 20),
                new MGSimpleTestSettings(0, "parent")
        );

        assertTrue(area.isInside(new MGLocation(0, 0, 0)));
        assertTrue(area.isInside(new MGLocation(20, 20, 20)));
        assertTrue(area.isInside(new MGLocation(15, 15, 20)));

        assertFalse(area.isInside(new MGLocation(-1, 0, 0)));
        assertFalse(area.isInside(new MGLocation(21, 5, 5)));
        assertFalse(area.isInside(new MGLocation(5, 25, 5)));
        assertFalse(area.isInside(new MGLocation(2, 5, 25)));
    }

    @Test
    public void testGetActiveSettings() {
        MGArea area = new MGArea(
                new MGLocation(0, 0, 0),
                new MGLocation(20, 20, 20),
                new MGSimpleTestSettings(0, "parent")
        );

        MGArea child1 = new MGArea(
                new MGLocation(0, 0, 0),
                new MGLocation(10, 10, 10),
                new MGSimpleTestSettings(5, "child1")
        );

        MGArea child2 = new MGArea(
                new MGLocation(0, 0, 0),
                new MGLocation(5, 5, 5),
                new MGSimpleTestSettings(10, "child2")
        );

        MGArea child3 = new MGArea(
                new MGLocation(8, 8, 8),
                new MGLocation(12, 12, 12),
                new MGSimpleTestSettings(20, "child3")
        );

        child1.addChildArea(child2);

        area.addChildArea(child1);
        area.addChildArea(child3);

        // While making checks on area
        // outside the boundaries of area should return null
        // between 0,0,0 and 5,5,5 child2 should be returned
        // between 6,6,6 and 7,7,7 child1 should be returned because child3 has a higher priority between 8,8,8 and 12,12,12
        // between 8,8,8 and 12,12,12 child3 should be returned
        // otherwise parent should be returned

        assertNull(area.getActiveSettings(new MGLocation(-1, -1, 0)));

        assertNotNull(area.getActiveSettings(new MGLocation(5, 5, 5)));

        assertEquals("child3", ((MGSimpleTestSettings) Objects.requireNonNull(area.getActiveSettings(new MGLocation(8, 8, 8)))).getMessage());
        assertEquals("child3", ((MGSimpleTestSettings) Objects.requireNonNull(area.getActiveSettings(new MGLocation(12, 12, 12)))).getMessage());

        assertEquals("child2", ((MGSimpleTestSettings) Objects.requireNonNull(area.getActiveSettings(new MGLocation(0, 0, 0)))).getMessage());
        assertEquals("child2", ((MGSimpleTestSettings) Objects.requireNonNull(area.getActiveSettings(new MGLocation(5, 5, 5)))).getMessage());

        assertEquals("child1", ((MGSimpleTestSettings) Objects.requireNonNull(area.getActiveSettings(new MGLocation(6, 6, 6)))).getMessage());
        assertEquals("child1", ((MGSimpleTestSettings) Objects.requireNonNull(area.getActiveSettings(new MGLocation(7, 7, 7)))).getMessage());

        assertEquals("parent", ((MGSimpleTestSettings) Objects.requireNonNull(area.getActiveSettings(new MGLocation(12, 13, 12)))).getMessage());
    }

    @Test
    public void testAddChild() {
        MGArea area = new MGArea(
                new MGLocation(0, 0, 0),
                new MGLocation(20, 20, 20),
                new MGSimpleTestSettings(0, "parent")
        );

        MGArea insideChild = new MGArea(
                new MGLocation(0, 0, 0),
                new MGLocation(10, 10, 10),
                new MGSimpleTestSettings(5, "insideChild")
        );

        MGArea outsideChild = new MGArea(
                new MGLocation(0, 0, 0),
                new MGLocation(25, 25, 25),
                new MGSimpleTestSettings(5, "outsideChild")
        );

        assertTrue(area.addChildArea(insideChild));
        assertFalse(area.addChildArea(insideChild)); //Attempting to add the same child again
        assertFalse(area.addChildArea(null));

        assertThrows(IllegalArgumentException.class, () -> area.addChildArea(outsideChild));//Attempting to add a child that extends outside the area
    }

    @Test
    public void testRemoveChild() {
        MGArea area = new MGArea(
                new MGLocation(0, 0, 0),
                new MGLocation(20, 20, 20),
                new MGSimpleTestSettings(0, "parent")
        );

        MGArea child = new MGArea(
                new MGLocation(0, 0, 0),
                new MGLocation(10, 10, 10),
                new MGSimpleTestSettings(5, "child1")
        );

        area.addChildArea(child);

        assertTrue(area.removeChildArea(child));
        assertFalse(area.removeChildArea(child)); //Attempting to remove a child that does not exist
        assertFalse(area.removeChildArea(null));
    }

}
