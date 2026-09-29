package com.fasterxml.jackson.core;

import org.junit.Test;
import org.junit.Assert;

public class JsonStreamContextgetEntryCountTest {
    @Test
    public void testGetEntryCount() throws Exception {
        // Create a JsonStreamContext instance using the constructor that sets _index
        JsonStreamContext context = new JsonStreamContext(1, 5) {
            // Implement required abstract method
            @Override
            public String getCurrentName() {
                return "dummy";
            }

            // Implement missing abstract method
            @Override
            public JsonStreamContext getParent() {
                return null;
            }
        };

        // Verify that getEntryCount returns _index + 1
        Assert.assertEquals(6, context.getEntryCount());
    }
}
