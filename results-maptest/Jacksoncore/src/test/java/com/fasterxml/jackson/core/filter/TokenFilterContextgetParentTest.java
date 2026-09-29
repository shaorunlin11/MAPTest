package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import static org.junit.Assert.*;

public class TokenFilterContextgetParentTest {

    @Test
    public void testGetParentReturnsParentField() throws Exception {
        // Create a parent context
        TokenFilterContext parentContext = new TokenFilterContext(0, null, null, false);

        // Create a child context with the parent
        TokenFilterContext childContext = new TokenFilterContext(1, parentContext, null, false);

        // Verify that getParent returns the correct parent
        assertEquals("getParent should return the parent context", parentContext, childContext.getParent());
    }

    @Test
    public void testGetParentReturnsNullWhenNoParent() throws Exception {
        // Create a context with no parent
        TokenFilterContext context = new TokenFilterContext(0, null, null, false);

        // Verify that getParent returns null
        assertNull("getParent should return null when there is no parent", context.getParent());
    }
}
