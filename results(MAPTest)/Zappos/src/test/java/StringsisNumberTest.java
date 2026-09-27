package com.zappos.json.util;
import org.junit.Test;
import org.junit.Assert;
public class StringsisNumberTest {
    @Test
    public void testIsNumber_NullInput() {
        Assert.assertFalse(Strings.isNumber(null));
    }

    @Test
    public void testIsNumber_EmptyString() {
        Assert.assertFalse(Strings.isNumber(""));
    }

    @Test
    public void testIsNumber_InvalidStartCharacter() {
        Assert.assertFalse(Strings.isNumber("abc123"));
        Assert.assertFalse(Strings.isNumber("@123"));
    }

    @Test
    public void testIsNumber_ValidInteger() {
        Assert.assertTrue(Strings.isNumber("123"));
        Assert.assertTrue(Strings.isNumber("-456"));
    }

    @Test
    public void testIsNumber_ValidDecimal() {
        Assert.assertTrue(Strings.isNumber("123.45"));
        Assert.assertTrue(Strings.isNumber("-123.45"));
    }

    @Test
    public void testIsNumber_MultipleDecimals() {
        Assert.assertFalse(Strings.isNumber("123.45.67"));
        Assert.assertFalse(Strings.isNumber("-123.45.67"));
    }

    @Test
    public void testIsNumber_InvalidCharacter() {
        Assert.assertFalse(Strings.isNumber("123a"));
        Assert.assertFalse(Strings.isNumber("123.45a"));
        Assert.assertFalse(Strings.isNumber("-123.45a"));
    }

}
