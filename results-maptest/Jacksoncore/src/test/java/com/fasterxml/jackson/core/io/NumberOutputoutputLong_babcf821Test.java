package com.fasterxml.jackson.core.io;

import org.junit.Test;
import static org.junit.Assert.*;

public class NumberOutputoutputLong_babcf821Test {

    @Test
    public void testOutputLong_PositiveWithinIntegerRange() {
        byte[] buffer = new byte[10];
        int result = NumberOutput.outputLong(123456789L, buffer, 0);
        assertEquals(9, result);
        assertEquals("123456789", new String(buffer, 0, result));
    }

    @Test
    public void testOutputLong_NegativeWithinIntegerRange() {
        byte[] buffer = new byte[10];
        int result = NumberOutput.outputLong(-123456789L, buffer, 0);
        assertEquals(10, result);
        assertEquals("-123456789", new String(buffer, 0, result));
    }

    @Test
    public void testOutputLong_LongMinValue() {
        byte[] buffer = new byte[20];
        int result = NumberOutput.outputLong(Long.MIN_VALUE, buffer, 0);
        assertEquals(20, result);
        assertEquals("-9223372036854775808", new String(buffer, 0, result));
    }

    @Test
    public void testOutputLong_LargeValue() {
        byte[] buffer = new byte[20];
        int result = NumberOutput.outputLong(1234567890123L, buffer, 0);
        assertEquals(13, result);
        assertEquals("1234567890123", new String(buffer, 0, result));
    }

    @Test
    public void testOutputLong_Zero() {
        byte[] buffer = new byte[10];
        int result = NumberOutput.outputLong(0L, buffer, 0);
        assertEquals(1, result);
        assertEquals("0", new String(buffer, 0, result));
    }

    @Test
    public void testOutputLong_NegativeLargeValue() {
        byte[] buffer = new byte[20];
        int result = NumberOutput.outputLong(-1234567890123L, buffer, 0);
        assertEquals(14, result);
        assertEquals("-1234567890123", new String(buffer, 0, result));
    }

@Test
    public void testOutputLong_TargetLines218() {
        // Target lines 218: upper < BILLION_L is false
        // We need to ensure:
        // - v < 0L is false
        // - v <= MAX_INT_AS_LONG is false
        // - upper < BILLION_L is false
        // So we need a value that is larger than MAX_INT_AS_LONG and causes upper to be >= BILLION_L

        // Let's use a value that is larger than MAX_INT_AS_LONG (which is 2^31-1 = 2147483647)
        // For example: 2147483648L (which is 2^31)
        // This will cause v > MAX_INT_AS_LONG, so the code will go into the else block
        // Then upper = v / BILLION_L (which is 1000000000)
        // 2147483648 / 1000000000 = 2 (since 2*1000000000 = 2000000000 < 2147483648)
        // So upper = 2, which is < BILLION_L (1000000000), so the code would go into the if block
        // But we need upper >= BILLION_L, so let's pick a larger value

        // Let's use 1000000000 * 1000000000 + 1 = 1000000000000000001L
        // This will make upper = 1000000000, which is equal to BILLION_L
        // So upper < BILLION_L is false, which hits the target line 218

        byte[] buffer = new byte[20];
        long v = 1000000000000000001L;
        int result = NumberOutput.outputLong(v, buffer, 0);

        // The expected output should be "1000000000000000001"
        assertEquals("1000000000000000001", new String(buffer, 0, result));
    }
}
