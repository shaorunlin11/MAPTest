package com.fasterxml.jackson.core.json;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonReadContextgetCurrentValueTest {

    @Test
    public void testGetCurrentValue() throws Exception {
        // Create a JsonReadContext instance with a known _currentValue
        JsonReadContext context = new JsonReadContext(null, null, 0, 1, 1);
        Object testValue = "testValue";
        context._currentValue = testValue;

        // Call the method under test
        Object result = context.getCurrentValue();

        // Verify the result
        assertEquals("Expected getCurrentValue to return the stored _currentValue", testValue, result);
    }
}
