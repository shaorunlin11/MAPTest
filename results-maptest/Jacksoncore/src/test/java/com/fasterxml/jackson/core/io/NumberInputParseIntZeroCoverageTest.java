package com.fasterxml.jackson.core.io;

import org.junit.Test;

public class NumberInputParseIntZeroCoverageTest {
    @Test
    public void testParseIntWithValidInput() {
        String s = "123456789";
        NumberInput.parseInt(s);
    }

@Test
    public void testParseIntWithNegativeInput() {
        String s = "-1234567890";
        NumberInput.parseInt(s);
    }

@Test
    public void testParseIntWithSingleMinusSign() {
        String s = "-0";
        NumberInput.parseInt(s);
    }

@Test(expected = java.lang.NumberFormatException.class)
    public void testParseIntWithSingleMinusSignAndLengthOne() {
        String s = "-";
        NumberInput.parseInt(s);
    }

@Test
    public void testParseIntWithThreeDigitsAfterSign() {
        String s = "-123";
        NumberInput.parseInt(s);
    }

@Test
    public void testParseIntWithLongPositiveNumber() {
        String s = "2147483647"; // Maximum value for int
        NumberInput.parseInt(s);
    }
}
