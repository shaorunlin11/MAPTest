package com.fasterxml.jackson.core.io;

import org.junit.Test;
import static org.junit.Assert.*;

public class NumberInputparseLong_a156b8d5Test {

    @Test
    public void testParseLongWithShortString() {
        String shortStr = "12345";
        long result = NumberInput.parseLong(shortStr);
        assertEquals(12345, result);
    }

    @Test
    public void testParseLongWithLongString() {
        String longStr = "1234567890";
        long result = NumberInput.parseLong(longStr);
        assertEquals(1234567890, result);
    }

    @Test
    public void testParseLongWithMinLongValue() {
        String minLongStr = String.valueOf(Long.MIN_VALUE);
        long result = NumberInput.parseLong(minLongStr);
        assertEquals(Long.MIN_VALUE, result);
    }

    @Test
    public void testParseLongWithMaxLongValue() {
        String maxLongStr = String.valueOf(Long.MAX_VALUE);
        long result = NumberInput.parseLong(maxLongStr);
        assertEquals(Long.MAX_VALUE, result);
    }

    @Test
    public void testParseLongWithZero() {
        String zeroStr = "0";
        long result = NumberInput.parseLong(zeroStr);
        assertEquals(0, result);
    }

    @Test
    public void testParseLongWithPositiveNumber() {
        String positiveStr = "123456789";
        long result = NumberInput.parseLong(positiveStr);
        assertEquals(123456789, result);
    }

    @Test
    public void testParseLongWithNegativeNumber() {
        String negativeStr = "-123456789";
        long result = NumberInput.parseLong(negativeStr);
        assertEquals(-123456789, result);
    }

    @Test
    public void testParseLongWithLeadingZeros() {
        String leadingZerosStr = "000123";
        long result = NumberInput.parseLong(leadingZerosStr);
        assertEquals(123, result);
    }

    @Test
    public void testParseLongWithTrailingZeros() {
        String trailingZerosStr = "123000";
        long result = NumberInput.parseLong(trailingZerosStr);
        assertEquals(123000, result);
    }
}
