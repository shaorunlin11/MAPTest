package com.fasterxml.jackson.core.json;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;

public class JsonWriteContextwithDupDetectorTest {
    @Test
    public void testWithDupDetector() throws Exception {
        // Create a JsonWriteContext instance
        JsonWriteContext context = new JsonWriteContext(JsonWriteContext.STATUS_OK_AS_IS, null, null);

        // Call the method under test
        JsonWriteContext result = context.withDupDetector(null);

        // Verify that the method returns the same instance
        assertTrue("Method should return the same instance", result == context);

        // Use reflection to verify the _dups field was updated
        Field dupsField = JsonWriteContext.class.getDeclaredField("_dups");
        dupsField.setAccessible(true);
        assertNull("DupDetector should be set to null", dupsField.get(context));
    }

    @Test
    public void testWithDupDetectorNull() throws Exception {
        // Create a JsonWriteContext instance
        JsonWriteContext context = new JsonWriteContext(JsonWriteContext.STATUS_OK_AS_IS, null, null);

        // Call the method with null
        JsonWriteContext result = context.withDupDetector(null);

        // Verify that the method returns the same instance
        assertTrue("Method should return the same instance", result == context);

        // Use reflection to verify the _dups field was updated to null
        Field dupsField = JsonWriteContext.class.getDeclaredField("_dups");
        dupsField.setAccessible(true);
        assertNull("DupDetector should be set to null", dupsField.get(context));
    }
}
