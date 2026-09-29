package com.fasterxml.jackson.core.io;

import org.junit.Test;
import static org.junit.Assert.*;

public class NumberInputparseAsDoubleTest {

    @Test
    public void testParseAsDouble_NullInput_ReturnsDefault() {
        double defaultValue = 123.45;
        double result = NumberInput.parseAsDouble(null, defaultValue);
        assertEquals(defaultValue, result, 0.0);
    }

    @Test
    public void testParseAsDouble_EmptyString_ReturnsDefault() {
        double defaultValue = 678.90;
        double result = NumberInput.parseAsDouble("", defaultValue);
        assertEquals(defaultValue, result, 0.0);
    }

    @Test
    public void testParseAsDouble_WhitespaceOnly_ReturnsDefault() {
        double defaultValue = 1.23;
        double result = NumberInput.parseAsDouble("   ", defaultValue);
        assertEquals(defaultValue, result, 0.0);
    }

    @Test
    public void testParseAsDouble_ValidDouble_ReturnsParsedValue() {
        double result = NumberInput.parseAsDouble("123.45", 0.0);
        assertEquals(123.45, result, 0.0);
    }

    @Test
    public void testParseAsDouble_InvalidFormat_ReturnsDefault() {
        double defaultValue = 9.87;
        double result = NumberInput.parseAsDouble("abc123", defaultValue);
        assertEquals(defaultValue, result, 0.0);
    }

    @Test
    public void testParseAsDouble_NastySmallDouble_ReturnsParsedValue() {
        double result = NumberInput.parseAsDouble(NumberInput.NASTY_SMALL_DOUBLE, 0.0);
        assertEquals(4.9E-324, result, 0.0);
    }

    @Test
    public void testParseAsDouble_LongString_ReturnsParsedValue() {
        String longStr = "9223372036854775807";
        double result = NumberInput.parseAsDouble(longStr, 0.0);
        assertEquals(9223372036854775807.0, result, 0.0);
    }

    @Test
    public void testParseAsDouble_MinLongString_ReturnsParsedValue() {
        String minLongStr = "-9223372036854775808";
        double result = NumberInput.parseAsDouble(minLongStr, 0.0);
        assertEquals(-9223372036854775808.0, result, 0.0);
    }
}
