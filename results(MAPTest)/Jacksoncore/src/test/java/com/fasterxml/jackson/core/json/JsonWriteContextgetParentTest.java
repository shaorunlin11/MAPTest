package com.fasterxml.jackson.core.json;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonWriteContextgetParentTest {
    @Test
    public void testGetParent_returnsParentField() throws Exception {
        // Create a parent context
        JsonWriteContext parentContext = new JsonWriteContext(0, null, null);

        // Create a child context with the parent
        JsonWriteContext childContext = new JsonWriteContext(1, parentContext, null);

        // Verify that getParent returns the correct parent
        assertEquals(parentContext, childContext.getParent());
    }

    @Test
    public void testGetParent_returnsNullWhenNoParent() throws Exception {
        // Create a context with no parent
        JsonWriteContext context = new JsonWriteContext(0, null, null);

        // Verify that getParent returns null
        assertNull(context.getParent());
    }
}
