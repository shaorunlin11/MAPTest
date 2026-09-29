package com.fasterxml.jackson.core;

import org.junit.Test;
import org.junit.Assert;

public class JsonStreamContexthasCurrentIndexTest {
    @Test
    public void testHasCurrentIndexWithNegativeIndex() throws Exception {
        // Create a subclass to access the protected fields
        JsonStreamContext context = new JsonStreamContext(0, -1) {
            @Override
            public String getCurrentName() {
                return null;
            }

            @Override
            public JsonStreamContext getParent() {
                return null;
            }
        };
        Assert.assertFalse(context.hasCurrentIndex());
    }

    @Test
    public void testHasCurrentIndexWithZeroIndex() throws Exception {
        // Create a subclass to access the protected fields
        JsonStreamContext context = new JsonStreamContext(0, 0) {
            @Override
            public String getCurrentName() {
                return null;
            }

            @Override
            public JsonStreamContext getParent() {
                return null;
            }
        };
        Assert.assertTrue(context.hasCurrentIndex());
    }

    @Test
    public void testHasCurrentIndexWithPositiveIndex() throws Exception {
        // Create a subclass to access the protected fields
        JsonStreamContext context = new JsonStreamContext(0, 1) {
            @Override
            public String getCurrentName() {
                return null;
            }

            @Override
            public JsonStreamContext getParent() {
                return null;
            }
        };
        Assert.assertTrue(context.hasCurrentIndex());
    }
}
