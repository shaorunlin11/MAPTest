package com.fasterxml.jackson.core.json;

import org.junit.Test;

public class JsonReadContextCreateChildObjectContextZeroCoverageTest {
    @Test
    public void testCreateChildObjectContext() throws Exception {
        // Create a mock or real instance of JsonReadContext
        JsonReadContext parent = new JsonReadContext(null, null, 0, 0, 0);

        // Set _child to null to trigger the code path for line 126
        parent._child = null;

        // Call the method under test
        parent.createChildObjectContext(1, 1);
    }
}
