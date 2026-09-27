package com.fasterxml.jackson.core.json;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonReadContextclearAndGetParentTest {

    @Test
    public void testClearAndGetParent() throws Exception {
        // Create a parent context
        JsonReadContext parent = new JsonReadContext(null, null, 0, 0, 0);

        // Create a child context
        JsonReadContext child = new JsonReadContext(parent, null, 0, 0, 0);

        // Set current value to non-null
        child._currentValue = "testValue";

        // Call the method
        JsonReadContext result = child.clearAndGetParent();

        // Verify that current value is null
        assertNull("Current value should be null after clearAndGetParent", child._currentValue);

        // Verify that the returned parent is correct
        assertEquals("Returned parent should match the original parent", parent, result);
    }
}
