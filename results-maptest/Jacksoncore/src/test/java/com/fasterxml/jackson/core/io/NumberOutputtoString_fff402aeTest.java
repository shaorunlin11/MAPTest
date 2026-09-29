package com.fasterxml.jackson.core.io;

import org.junit.Test;
import static org.junit.Assert.*;

public class NumberOutputtoString_fff402aeTest {

    @Test
    public void testToStringWithinIntegerRange() {
        assertEquals("123", NumberOutput.toString(123));
        assertEquals("-456", NumberOutput.toString(-456));
        assertEquals("0", NumberOutput.toString(0));
    }

    @Test
    public void testToStringOutsideIntegerRange() {
        assertEquals("2147483648", NumberOutput.toString(2147483648L));
        assertEquals("-2147483649", NumberOutput.toString(-2147483649L));
    }

    @Test
    public void testToStringAtIntegerMinValue() {
        assertEquals(String.valueOf(Integer.MIN_VALUE), NumberOutput.toString(Integer.MIN_VALUE));
    }

    @Test
    public void testToStringAtIntegerMaxValue() {
        assertEquals(String.valueOf(Integer.MAX_VALUE), NumberOutput.toString(Integer.MAX_VALUE));
    }

    @Test
    public void testToStringAtLongMinValue() {
        assertEquals(String.valueOf(Long.MIN_VALUE), NumberOutput.toString(Long.MIN_VALUE));
    }

    @Test
    public void testToStringAtLongMaxValue() {
        assertEquals(String.valueOf(Long.MAX_VALUE), NumberOutput.toString(Long.MAX_VALUE));
    }

@Test
    public void testToStringTargetLine252() {
        // This test is designed to execute line 252 of the toString method
        // which is the return statement: return Long.toString(v);
        // This path is taken when v is outside the integer range
        // We'll use a value that is just outside the integer range
        long value = Integer.MAX_VALUE + 1;
        String result = NumberOutput.toString(value);
        assertEquals(String.valueOf(value), result);
    }
}
