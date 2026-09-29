package com.fasterxml.jackson.core.json;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonWriteContextsetCurrentValueTest {
    @Test
    public void testSetCurrentValue() throws Exception {
        // Create a JsonWriteContext instance
        JsonWriteContext context = new JsonWriteContext(JsonWriteContext.STATUS_OK_AS_IS, null, null);

        // Test with a non-null value
        Object testValue = new Object();
        context.setCurrentValue(testValue);
        assertEquals("Current value should be set correctly", testValue, context._currentValue);

        // Test with null value
        context.setCurrentValue(null);
        assertNull("Current value should be null after setting to null", context._currentValue);
    }
}
