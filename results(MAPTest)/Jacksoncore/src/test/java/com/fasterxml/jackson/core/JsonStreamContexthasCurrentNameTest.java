package com.fasterxml.jackson.core;

import org.junit.Test;
import org.junit.Assert;

public class JsonStreamContexthasCurrentNameTest {
    @Test
    public void testHasCurrentName_ReturnsFalseWhenGetCurrentNameReturnsNull() {
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
        Assert.assertFalse(context.hasCurrentName());
    }

    @Test
    public void testHasCurrentName_ReturnsTrueWhenGetCurrentNameReturnsNonEmptyString() {
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
        Assert.assertTrue(context.hasCurrentName());
    }

    @Test
    public void testHasCurrentName_ReturnsTrueWhenGetCurrentNameReturnsNonBlankString() {
        JsonStreamContext context = new JsonStreamContext(0, 0) {
            @Override
            public String getCurrentName() {
                return "  test  ";
            }

            @Override
            public JsonStreamContext getParent() {
                return null;
            }
        };
        Assert.assertTrue(context.hasCurrentName());
    }
}
