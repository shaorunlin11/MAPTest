package com.fasterxml.jackson.core.io;
import org.junit.Test;
import static org.junit.Assert.*;
public class NumberInputparseAsIntTest {
    @Test
    public void testParseAsInt_NullInput_ReturnsDefault() {
        assertEquals(0, NumberInput.parseAsInt(null, 0));
        assertEquals(5, NumberInput.parseAsInt(null, 5));
    }

    @Test
    public void testParseAsInt_EmptyStringAfterTrim_ReturnsDefault() {
        assertEquals(0, NumberInput.parseAsInt("   ", 0));
        assertEquals(10, NumberInput.parseAsInt("\t\n", 10));
    }

    @Test
    public void testParseAsInt_ValidInteger_ReturnsParsedValue() {
        assertEquals(123, NumberInput.parseAsInt("123", 0));
        assertEquals(-456, NumberInput.parseAsInt("-456", 0));
        assertEquals(789, NumberInput.parseAsInt("+789", 0));
    }

    @Test
    public void testParseAsInt_StringWithNonDigitCharacters_TriesParseDouble() {
        assertEquals(123, NumberInput.parseAsInt("123.45", 0));
        assertEquals(123, NumberInput.parseAsInt("123.99", 0));
        assertEquals(0, NumberInput.parseAsInt("abc", 0));
    }

    @Test
    public void testParseAsInt_StringWithNonDigitCharactersAndInvalidDouble_ReturnsDefault() {
        assertEquals(0, NumberInput.parseAsInt("123.45.67", 0));
        assertEquals(0, NumberInput.parseAsInt("invalid", 0));
    }

    @Test
    public void testParseAsInt_StringThatIsAllDigitsButOutOfRange_ReturnsDefault() {
        assertEquals(0, NumberInput.parseAsInt("2147483648", 0)); // Integer.MAX_VALUE + 1
        assertEquals(0, NumberInput.parseAsInt("-2147483649", 0)); // Integer.MIN_VALUE - 1
    }

    @Test
    public void testParseAsInt_StringWithLeadingPlusSign_SkipsSign() {
        assertEquals(123, NumberInput.parseAsInt("+123", 0));
    }

    @Test
    public void testParseAsInt_StringWithLeadingMinusSign_SkipsSign() {
        assertEquals(-123, NumberInput.parseAsInt("-123", 0));
    }


    @Test
    public void testParseAsInt_StringThatCanBeConvertedToDoubleButNotToInt_ReturnsDefault() {
        assertEquals(123, NumberInput.parseAsInt("123.456", 0));
        assertEquals(123, NumberInput.parseAsInt("123.999", 0));
    }
}
