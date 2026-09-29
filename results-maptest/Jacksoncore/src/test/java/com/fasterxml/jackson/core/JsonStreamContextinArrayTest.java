package com.fasterxml.jackson.core;

import org.junit.Test;
import org.junit.Assert;

public class JsonStreamContextinArrayTest {
    @Test
    public void testInArrayWhenTypeIsArray() throws Exception {
        // Create a JsonStreamContext instance with TYPE_ARRAY
        JsonStreamContext context = new JsonStreamContext(1, 0) {
            @Override
            public String getCurrentName() {
                return "test";
            }

            @Override
            public JsonStreamContext getParent() {
                return null;
            }
        };

        // Verify that inArray returns true
        Assert.assertTrue(context.inArray());
    }

    @Test
    public void testInArrayWhenTypeIsRoot() throws Exception {
        // Create a JsonStreamContext instance with TYPE_ROOT
        JsonStreamContext context = new JsonStreamContext(0, 0) {
            @Override
            public String getCurrentName() {
                return "test";
            }

            @Override
            public JsonStreamContext getParent() {
                return null;
            }
        };

        // Verify that inArray returns false
        Assert.assertFalse(context.inArray());
    }

    @Test
    public void testInArrayWhenTypeisObject() throws Exception {
        // Create a JsonStreamContext instance with TYPE_OBJECT
        JsonStreamContext context = new JsonStreamContext(2, 0) {
            @Override
            public String getCurrentName() {
                return "test";
            }

            @Override
            public JsonStreamContext getParent() {
                return null;
            }
        };

        // Verify that inArray returns false
        Assert.assertFalse(context.inArray());
    }
}
