package com.fasterxml.jackson.core.io;

import org.junit.Test;
import static org.junit.Assert.*;

public class NumberOutputoutputLong_407621e6Test {

    @Test
    public void testOutputLong_PositiveWithinIntegerRange() {
        char[] buffer = new char[20];
        int off = 0;
        int result = NumberOutput.outputLong(12345, buffer, off);
        assertEquals("12345", new String(buffer, 0, result));
        assertEquals(5, result);
    }

    @Test
    public void testOutputLong_NegativeWithinIntegerRange() {
        char[] buffer = new char[20];
        int off = 0;
        int result = NumberOutput.outputLong(-12345, buffer, off);
        assertEquals("-12345", new String(buffer, 0, result));
        assertEquals(6, result);
    }

    @Test
    public void testOutputLong_LongMinValue() {
        char[] buffer = new char[20];
        int off = 0;
        int result = NumberOutput.outputLong(Long.MIN_VALUE, buffer, off);
        assertEquals("-9223372036854775808", new String(buffer, 0, result));
        assertEquals(20, result);
    }

    @Test
    public void testOutputLong_PositiveLargeValue() {
        char[] buffer = new char[20];
        int off = 0;
        int result = NumberOutput.outputLong(1234567890123L, buffer, off);
        assertEquals("1234567890123", new String(buffer, 0, result));
        assertEquals(13, result);
    }

    @Test
    public void testOutputLong_NegativeLargeValue() {
        char[] buffer = new char[20];
        int off = 0;
        int result = NumberOutput.outputLong(-1234567890123L, buffer, off);
        assertEquals("-1234567890123", new String(buffer, 0, result));
        assertEquals(14, result);
    }

    @Test
    public void testOutputLong_Zero() {
        char[] buffer = new char[20];
        int off = 0;
        int result = NumberOutput.outputLong(0, buffer, off);
        assertEquals("0", new String(buffer, 0, result));
        assertEquals(1, result);
    }

    @Test
    public void testOutputLong_MaxIntValue() {
        char[] buffer = new char[20];
        int off = 0;
        int result = NumberOutput.outputLong(Integer.MAX_VALUE, buffer, off);
        assertEquals("2147483647", new String(buffer, 0, result));
        assertEquals(10, result);
    }

    @Test
    public void testOutputLong_MinIntValue() {
        char[] buffer = new char[20];
        int off = 0;
        int result = NumberOutput.outputLong(Integer.MIN_VALUE, buffer, off);
        assertEquals("-2147483648", new String(buffer, 0, result));
        assertEquals(11, result);
    }

@Test
    public void testOutputLong_PositiveLargeValueWithSpecificPath() {
        char[] buffer = new char[20];
        int off = 0;
        // This value is chosen to satisfy the conditions:
        // v >= 0L
        // v > MAX_INT_AS_LONG
        // upper >= BILLION_L
        long v = 1234567890123456789L;
        int result = NumberOutput.outputLong(v, buffer, off);
        assertEquals("1234567890123456789", new String(buffer, 0, result));
        assertEquals(19, result);
    }
}
