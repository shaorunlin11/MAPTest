package com.fasterxml.jackson.core.io;

import org.junit.Test;
import static org.junit.Assert.*;

public class NumberOutputtoString_280413f2Test {

    @Test
    public void testToString_positiveWithinSmallIntStrs() {
        assertEquals("0", NumberOutput.toString(0));
        assertEquals("1", NumberOutput.toString(1));
        assertEquals("10", NumberOutput.toString(10));
    }

    @Test
    public void testToString_negativeWithinSmallIntStrs2() {
        assertEquals("-1", NumberOutput.toString(-1));
        assertEquals("-10", NumberOutput.toString(-10));
    }

    @Test
    public void testToString_positiveOutsideSmallIntStrs() {
        assertEquals("11", NumberOutput.toString(11));
    }

    @Test
    public void testToString_negativeOutsideSmallIntStrs2() {
        assertEquals("-11", NumberOutput.toString(-11));
    }

    @Test
    public void testToString_maxInt() {
        assertEquals(String.valueOf(Integer.MAX_VALUE), NumberOutput.toString(Integer.MAX_VALUE));
    }

    @Test
    public void testToString_minInt() {
        assertEquals(String.valueOf(Integer.MIN_VALUE), NumberOutput.toString(Integer.MIN_VALUE));
    }

    @Test
    public void testToString_zero() {
        assertEquals("0", NumberOutput.toString(0));
    }

    @Test
    public void testToString_largePositive() {
        assertEquals("1234567890", NumberOutput.toString(1234567890));
    }

    @Test
    public void testToString_largeNegative() {
        assertEquals("-1234567890", NumberOutput.toString(-1234567890));
    }
}
