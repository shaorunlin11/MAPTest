package org.apache.commons.cli;

import org.junit.Test;

public class TypeHandlerCreateNumberZeroCoverageTest {
    @Test
    public void testCreateNumberWithDecimal() throws ParseException {
        String str = "123.45";
        Number result = TypeHandler.createNumber(str);
        // This test ensures that the code path for strings containing '.' is executed
        // and verifies that the returned value is a Double.
        assert result instanceof Double;
    }

@Test
    public void testCreateNumberWithoutDecimal() throws ParseException {
        String str = "123";
        Number result = TypeHandler.createNumber(str);
        // This test ensures that the code path for strings without '.' is executed
        // and verifies that the returned value is a Long.
        assert result instanceof Long;
    }

@Test(expected = ParseException.class)
    public void testCreateNumberThrowsParseException() throws ParseException {
        String str = "invalid";
        TypeHandler.createNumber(str);
    }
}
