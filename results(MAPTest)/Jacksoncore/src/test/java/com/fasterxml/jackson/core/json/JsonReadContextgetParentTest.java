package com.fasterxml.jackson.core.json;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonReadContextgetParentTest {

    @Test
    public void testGetParentReturnsCorrectValue() throws Exception {
        // Create a parent context
        JsonReadContext parentContext = new JsonReadContext(null, null, 0, 0, 0);

        // Create a child context with the parent
        JsonReadContext childContext = new JsonReadContext(parentContext, null, 0, 0, 0);

        // Verify that getParent returns the correct parent
        assertEquals(parentContext, childContext.getParent());
    }

    @Test
    public void testGetParentReturnsNullForRootContext() throws Exception {
        // Create a root context (no parent)
        JsonReadContext rootContext = new JsonReadContext(null, null, 0, 0, 0);

        // Verify that getParent returns null
        assertNull(rootContext.getParent());
    }
}
