package com.fasterxml.jackson.core;

import org.junit.Test;
import org.junit.Assert;

public class JsonStreamContextgetCurrentIndexTest {
    @Test
    public void testGetCurrentIndexWithNegativeIndex() throws Exception {
        // Create a subclass to access protected fields
        class TestJsonStreamContext extends JsonStreamContext {
            public TestJsonStreamContext(int type, int index) {
                super(type, index);
            }

            // Implement abstract method from superclass
            @Override
            public String getCurrentName() {
                return "test";
            }

            // Implement missing abstract method
            @Override
            public JsonStreamContext getParent() {
                return null;
            }
        }

        TestJsonStreamContext context = new TestJsonStreamContext(JsonStreamContext.TYPE_ARRAY, -1);
        Assert.assertEquals(0, context.getCurrentIndex());
    }

    @Test
    public void testGetCurrentIndexWithNonNegativeIndex() throws Exception {
        // Create a subclass to access protected fields
        class TestJsonStreamContext extends JsonStreamContext {
            public TestJsonStreamContext(int type, int index) {
                super(type, index);
            }

            // Implement abstract method from superclass
            @Override
            public String getCurrentName() {
                return "test";
            }

            // Implement missing abstract method
            @Override
            public JsonStreamContext getParent() {
                return null;
            }
        }

        TestJsonStreamContext context = new TestJsonStreamContext(JsonStreamContext.TYPE_OBJECT, 5);
        Assert.assertEquals(5, context.getCurrentIndex());
    }
}
