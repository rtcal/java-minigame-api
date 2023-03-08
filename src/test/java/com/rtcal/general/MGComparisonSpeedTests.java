package com.rtcal.general;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MGComparisonSpeedTests {

    @Test
    public void compareBitwiseAndRegularCheck() {
        // 'Regular' wins by approximately 2x the speed

        int min = 20000000;
        int max = 50000000;

        long elapsedTime;

        long startTime = System.nanoTime();
        int count = 0;

        for (int i = 0; i <= 100000000; i++) {
            if (i >= min && i <= max) count++;
        }

        elapsedTime = System.nanoTime() - startTime;
        assertEquals(count, max - min + 1);
        System.out.println("Regular elapsed in " + elapsedTime);

        startTime = System.nanoTime();
        count = 0;

        for (int i = 0; i <= 100000000; i++) {
            if (((max - i) | (i - min)) >= 0) count++;
        }

        elapsedTime = System.nanoTime() - startTime;
        assertEquals(count, max - min + 1);
        System.out.println("Bitwise elapsed in " + elapsedTime);
    }

}
