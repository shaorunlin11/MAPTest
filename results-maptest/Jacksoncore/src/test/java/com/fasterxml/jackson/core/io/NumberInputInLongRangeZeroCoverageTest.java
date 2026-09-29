package com.fasterxml.jackson.core.io;

import org.junit.Test;

public class NumberInputInLongRangeZeroCoverageTest {
    @Test
    public void testInLongRange() {
        // Define test values for target line 146
        char[] ch = {'9', '2', '2', '3', '3', '3', '7', '2', '0', '3', '6', '8', '5', '4', '7', '7', '5', '8', '0', '7'};
        int off = 0;
        int len = 20;
        boolean negative = false;

        // Execute the method under test
        boolean result = NumberInput.inLongRange(ch, off, len, negative);
    }

@Test
    public void testInLongRangeWithNegative() {
        // Define test values for target line 146 with negative true
        char[] ch = {'9', '2', '2', '3', '3', '3', '7', '2', '0', '3', '6', '8', '5', '4', '7', '7', '5', '8', '0', '7'};
        int off = 0;
        int len = 20;
        boolean negative = true;

        // Execute the method under test
        boolean result = NumberInput.inLongRange(ch, off, len, negative);
    }

@Test
    public void testInLongRangeWithMinLongStrNoSign() {
        // Define test values to hit target lines 148
        char[] ch = {'-','2','1','4','7','4','8','3','6','4','7'};
        int off = 1;
        int len = 10;
        boolean negative = true;

        // Execute the method under test
        boolean result = NumberInput.inLongRange(ch, off, len, negative);
    }

@Test
    public void testInLongRangeWithMaxLongStr() {
        // Define test values to hit target lines 148
        char[] ch = {'2','1','4','7','4','8','3','6','4','7'};
        int off = 0;
        int len = 10;
        boolean negative = false;

        // Execute the method under test
        boolean result = NumberInput.inLongRange(ch, off, len, negative);
    }
}
