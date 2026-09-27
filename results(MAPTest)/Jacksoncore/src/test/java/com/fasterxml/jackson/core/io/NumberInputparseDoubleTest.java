package com.fasterxml.jackson.core.io;

import org.junit.Test;
import org.junit.Assert;

import java.lang.reflect.Method;

public class NumberInputparseDoubleTest {

    @Test
    public void testParseDoubleWithNastySmallDouble() {
        double result = NumberInput.parseDouble(NumberInput.NASTY_SMALL_DOUBLE);
        Assert.assertEquals(Double.MIN_VALUE, result, 0.0);
    }

    @Test
    public void testParseDoubleWithValidDoubleString() {
        double result = NumberInput.parseDouble("123.456");
        Assert.assertEquals(123.456, result, 0.0);
    }

    @Test(expected = NumberFormatException.class)
    public void testParseDoubleWithInvalidDoubleString() throws NumberFormatException {
        NumberInput.parseDouble("invalid");
    }

    @Test(expected = NullPointerException.class)
    public void testParseDoubleWithNullString() throws NumberFormatException {
        NumberInput.parseDouble(null);
    }
}
