package org.apache.commons.cli;

import org.junit.Test;

public class TypeHandlerCreateValueZeroCoverage_168Test {
    @Test
    public void testCreateValueWithStringValue() throws ParseException {
        String input = "test";
        Class<String> clazz = String.class;
        Object result = TypeHandler.createValue(input, clazz);
        assert result == input;
    }

@Test
    public void testCreateValueWithNumberValue() throws ParseException {
        String input = "123";
        Class<Number> clazz = Number.class;
        Object result = TypeHandler.createValue(input, clazz);
        assert result instanceof Number;
    }

@Test
    public void testCreateValueWithClassValue() throws ParseException {
        String input = "java.lang.String";
        Class<Class> clazz = Class.class;
        Object result = TypeHandler.createValue(input, clazz);
        assert result instanceof Class;
    }
}
