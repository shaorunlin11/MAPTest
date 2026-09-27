package com.fasterxml.jackson.core.io;

import org.junit.Test;

import java.math.BigDecimal;

public class NumberInputParseBigDecimalZeroCoverageTest {
    @Test
    public void testParseBigDecimalValidInput() throws Exception {
        String validBigDecimal = "12345678901234567890.1234567890";
        BigDecimal result = NumberInput.parseBigDecimal(validBigDecimal);
        // Ensure that the parsed value matches the input string
        assert result.toString().equals(validBigDecimal);
    }

@Test(expected = NumberFormatException.class)
    public void testParseBigDecimalInvalidInput() throws Exception {
        String invalidBigDecimal = "123.45.67";
        NumberInput.parseBigDecimal(invalidBigDecimal);
    }
}
