package com.fasterxml.jackson.core.json;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.Constructor;


public class JsonWriteContextgetCurrentValueTest {
    @Test
    public void testGetCurrentValue() throws Exception {
        // Create a JsonWriteContext instance using reflection to bypass private constructor
        Class<?> jsonWriteContextClass = Class.forName("com.fasterxml.jackson.core.json.JsonWriteContext");
        Constructor<?> constructor = jsonWriteContextClass.getDeclaredConstructor(int.class, JsonWriteContext.class, DupDetector.class);
        constructor.setAccessible(true);

        // Create a mock DupDetector instance (since it's a protected field, we don't need to initialize it)
        Object dupDetector = null;

        // Create an instance of JsonWriteContext
        JsonWriteContext context = (JsonWriteContext) constructor.newInstance(0, null, dupDetector);

        // Set _currentValue to a known value
        Field currentValueField = jsonWriteContextClass.getDeclaredField("_currentValue");
        currentValueField.setAccessible(true);
        currentValueField.set(context, "testValue");

        // Call the method and verify the result
        Object result = context.getCurrentValue();
        assertEquals("testValue", result);

        // Test with null value
        currentValueField.set(context, null);
        assertNull(context.getCurrentValue());
    }
}
