package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonParserNumberTypeTest {
    @Test
    public void testNumberTypeEnumConstants() {
        assertEquals("INT", JsonParser.NumberType.INT.name());
        assertEquals("LONG", JsonParser.NumberType.LONG.name());
        assertEquals("BIG_INTEGER", JsonParser.NumberType.BIG_INTEGER.name());
        assertEquals("FLOAT", JsonParser.NumberType.FLOAT.name());
        assertEquals("DOUBLE", JsonParser.NumberType.DOUBLE.name());
        assertEquals("BIG_DECIMAL", JsonParser.NumberType.BIG_DECIMAL.name());
    }
}
