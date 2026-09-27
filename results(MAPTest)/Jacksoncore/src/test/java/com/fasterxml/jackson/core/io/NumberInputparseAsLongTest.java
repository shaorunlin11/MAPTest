package com.fasterxml.jackson.core.io;

import org.junit.Test;
import static org.junit.Assert.*;

public class NumberInputparseAsLongTest {

    @Test
    public void testParseAsLong_NullInput_ReturnsDefault() {
        long result = NumberInput.parseAsLong(null, 123);
        assertEquals(123, result);
    }

    @Test
    public void testParseAsLong_EmptyString_ReturnsDefault() {
        long result = NumberInput.parseAsLong("", 456);
        assertEquals(456, result);
    }

    @Test
    public void testParseAsLong_WhitespaceOnly_ReturnsDefault() {
        long result = NumberInput.parseAsLong("   ", 789);
        assertEquals(789, result);
    }

    @Test
    public void testParseAsLong_ValidInteger_ReturnsParsedValue() {
        long result = NumberInput.parseAsLong("12345", 0);
        assertEquals(12345, result);
    }

    @Test
    public void testParseAsLong_IntegerWithPlusSign_ReturnsParsedValue() {
        long result = NumberInput.parseAsLong("+12345", 0);
        assertEquals(12345, result);
    }

    @Test
    public void testParseAsLong_IntegerWithMinusSign_ReturnsParsedValue() {
        long result = NumberInput.parseAsLong("-12345", 0);
        assertEquals(-12345, result);
    }

    @Test
    public void testParseAsLong_StringWithNonDigit_ReturnsDoubleParsedValue() {
        long result = NumberInput.parseAsLong("123.45", 0);
        assertEquals(123, result);
    }

    @Test
    public void testParseAsLong_StringWithNonDigitAndInvalidDouble_ReturnsDefault() {
        long result = NumberInput.parseAsLong("abc", 0);
        assertEquals(0, result);
    }

    @Test
    public void testParseAsLong_StringWithNonDigitAndValidDouble_ReturnsDoubleParsedValue() {
        long result = NumberInput.parseAsLong("123.456", 0);
        assertEquals(123, result);
    }

    @Test
    public void testParseAsLong_StringWithNonDigitAndValidDoubleWithExponent_ReturnsDoubleParsedValue() {
        long result = NumberInput.parseAsLong("1.23e4", 0);
        assertEquals(12300, result);
    }

    @Test
    public void testParseAsLong_StringWithNonDigitAndValidDoubleWithNegativeExponent_ReturnsDoubleParsedValue() {
        long result = NumberInput.parseAsLong("1.23e-4", 0);
        assertEquals(0, result);
    }

    @Test
    public void testParseAsLong_StringWithNonDigitAndValidDoubleWithScientificNotation_ReturnsDoubleParsedValue() {
        long result = NumberInput.parseAsLong("1.23e+4", 0);
        assertEquals(12300, result);
    }

    @Test
    public void testParseAsLong_StringWithNonDigitAndValidDoubleWithMultipleDecimalPoints_ReturnsDefault() {
        long result = NumberInput.parseAsLong("123.45.67", 0);
        assertEquals(0, result);
    }

    @Test
    public void testParseAsLong_StringWithNonDigitAndValidDoubleWithLeadingZeros_ReturnsDoubleParsedValue() {
        long result = NumberInput.parseAsLong("00123.45", 0);
        assertEquals(123, result);
    }

    @Test
    public void testParseAsLong_StringWithNonDigitAndValidDoubleWithTrailingZeros_ReturnsDoubleParsedValue() {
        long result = NumberInput.parseAsLong("123.4500", 0);
        assertEquals(123, result);
    }

    @Test
    public void testParseAsLong_StringWithNonDigitAndValidDoubleWithLeadingAndTrailingZeros_ReturnsDoubleParsedValue() {
        long result = NumberInput.parseAsLong("00123.4500", 0);
        assertEquals(123, result);
    }
}
