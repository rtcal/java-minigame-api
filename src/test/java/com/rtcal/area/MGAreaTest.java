package com.rtcal.area;


import com.rtcal.area.exceptions.MGDuplicateIDException;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class MGAreaTest {

    @Test
    public void testIsInside() throws MGDuplicateIDException {
        MGAreaManager areaManager = new MGAreaManager();

        MGArea area = new MGArea(
                "testInsideParent",
                new MGLocation(0, 0, 0),
                new MGLocation(20, 20, 20),
                0,
                areaManager
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
    public void testGetActiveSettings() throws MGDuplicateIDException {
        MGAreaManager areaManager = new MGAreaManager();

        MGArea area = new MGProtectedArea(
                "getActiveSettingsParent",
                new MGLocation(0, 0, 0),
                new MGLocation(20, 20, 20),
                new MGSimpleTestSettings("parent"),
                0,
                areaManager
        );

        MGArea child1 = new MGProtectedArea(
                "child1",
                new MGLocation(0, 0, 0),
                new MGLocation(10, 10, 10),
                new MGSimpleTestSettings("child1"),
                10,
                areaManager
        );

        MGArea child2 = new MGProtectedArea(
                "child2",
                new MGLocation(0, 0, 0),
                new MGLocation(5, 5, 5),
                new MGSimpleTestSettings("child2"),
                20,
                areaManager
        );

        MGArea child3 = new MGProtectedArea(
                "child3",
                new MGLocation(8, 8, 8),
                new MGLocation(12, 12, 12),
                new MGSimpleTestSettings("child3"),
                30,
                areaManager
        );

        MGArea child4 = new MGArea(
                "child4",
                new MGLocation(9, 9, 9),
                new MGLocation(11, 11, 11),
                40,
                areaManager
        );

        assertTrue(child1.addChildArea(child2));

        assertTrue(child3.addChildArea(child4));

        assertTrue(area.addChildArea(child1));
        assertTrue(area.addChildArea(child3));

        // While making checks on area
        // outside the boundaries of area should return null
        // between 0,0,0 and 5,5,5 child2 should be returned
        // between 6,6,6 and 7,7,7 child1 should be returned because child3 has a higher priority between 8,8,8 and 12,12,12
        // between 8,8,8 and 12,12,12 child3 should be returned
        // otherwise parent should be returned

        assertNull(area.getActiveArea(new MGLocation(-1, -1, 0)));

        assertNotNull(area.getActiveArea(new MGLocation(5, 5, 5)));

        assertEquals("child3", ((MGSimpleTestSettings) Objects.requireNonNull(area.getActiveArea(new MGLocation(8, 8, 8))).getSettings()).getMessage());
        assertEquals("child3", ((MGSimpleTestSettings) Objects.requireNonNull(area.getActiveArea(new MGLocation(12, 12, 12))).getSettings()).getMessage());
//
//        // Even though this location falls under the child4 object inside child3, because it has no settings, it should fall back to the parent (child3) settings
        assertEquals("child3", ((MGSimpleTestSettings) Objects.requireNonNull(area.getActiveArea(new MGLocation(10, 10, 10))).getSettings()).getMessage());

        assertEquals("child2", ((MGSimpleTestSettings) Objects.requireNonNull(area.getActiveArea(new MGLocation(0, 0, 0))).getSettings()).getMessage());
        assertEquals("child2", ((MGSimpleTestSettings) Objects.requireNonNull(area.getActiveArea(new MGLocation(5, 5, 5))).getSettings()).getMessage());

        assertEquals("child1", ((MGSimpleTestSettings) Objects.requireNonNull(area.getActiveArea(new MGLocation(6, 6, 6))).getSettings()).getMessage());
        assertEquals("child1", ((MGSimpleTestSettings) Objects.requireNonNull(area.getActiveArea(new MGLocation(7, 7, 7))).getSettings()).getMessage());

        assertEquals("parent", ((MGSimpleTestSettings) Objects.requireNonNull(area.getActiveArea(new MGLocation(12, 13, 12))).getSettings()).getMessage());
    }

    @Test
    public void testAddChild() throws MGDuplicateIDException {
        MGAreaManager areaManager = new MGAreaManager();

        MGArea area = new MGArea(
                "addChildParent",
                new MGLocation(0, 0, 0),
                new MGLocation(20, 20, 20),
                0,
                areaManager
        );

        MGArea insideChild = new MGArea(
                "insideChild",
                new MGLocation(0, 0, 0),
                new MGLocation(10, 10, 10),
                10,
                areaManager
        );

        MGArea insideProtectedChild = new MGProtectedArea(
                "insideProtectedChild",
                new MGLocation(0, 0, 0),
                new MGLocation(10, 10, 10),
                new MGSimpleTestSettings("insideProtectedChild"),
                20,
                areaManager
        );

        MGArea outsideChild = new MGArea(
                "outsideChild",
                new MGLocation(0, 0, 0),
                new MGLocation(25, 25, 25),
                30,
                areaManager
        );

        MGArea lowPriorityChild = new MGArea(
                "lowPriorityChild",
                new MGLocation(0, 0, 0),
                new MGLocation(1, 1, 1),
                -10,
                areaManager
        );

        assertTrue(area.addChildArea(insideChild));
        assertTrue(area.addChildArea(insideProtectedChild));
        assertFalse(area.addChildArea(insideChild)); //Attempting to add the same child again
        assertFalse(area.addChildArea(null));

        assertThrows(IllegalArgumentException.class, () -> area.addChildArea(outsideChild));//Attempting to add a child that extends outside the area
        assertThrows(IllegalArgumentException.class, () -> area.addChildArea(lowPriorityChild)); //Attempting to add a child with lower priority than parent
    }

    @Test
    public void testRemoveChild() throws MGDuplicateIDException {
        MGAreaManager areaManager = new MGAreaManager();

        MGArea area = new MGArea(
                "parent",
                new MGLocation(0, 0, 0),
                new MGLocation(20, 20, 20),
                0,
                areaManager
        );

        MGArea child = new MGArea(
                "child",
                new MGLocation(0, 0, 0),
                new MGLocation(10, 10, 10),
                10,
                areaManager
        );

        area.addChildArea(child);

        assertTrue(area.removeChildArea(child));
        assertFalse(area.removeChildArea(child)); //Attempting to remove a child that does not exist
        assertFalse(area.removeChildArea(null));
    }

    @Test
    public void testAreaNameAndID() {
        MGAreaManager areaManager = new MGAreaManager();

        String name = "baseArea";

        try {
            MGArea baseArea = new MGArea(
                    "baseArea",
                    new MGLocation(0, 0, 0),
                    new MGLocation(10, 10, 10),
                    0,
                    areaManager
            );

            final UUID uuid = baseArea.getID();

            assertNotNull(uuid);

            assertThrows(MGDuplicateIDException.class, () -> new MGArea(name,
                    new MGLocation(0, 0, 0),
                    new MGLocation(10, 10, 10),
                    0,
                    areaManager
            ));

            assertThrows(MGDuplicateIDException.class, () -> new MGArea("another", uuid,
                    new MGLocation(0, 0, 0),
                    new MGLocation(10, 10, 10),
                    0,
                    areaManager
            ));

        } catch (MGDuplicateIDException ignore) {
        }
    }

}
