package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonStreamContextinObjectTest {

    @Test
    public void testInObjectReturnsTrueWhenTypeIsObject() throws Exception {
        // Arrange
        JsonStreamContext context = new JsonStreamContext(2, 0) {
            @Override
            public String getCurrentName() {
                return null;
            }

            @Override
            public JsonStreamContext getParent() {
                return null;
            }
        };

        // Act
        boolean result = context.inObject();

        // Assert
        assertTrue(result);
    }

    @Test
    public void testInObjectReturnsFalseWhenTypeIsRoot() throws Exception {
        // Arrange
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

        // Act
        boolean result = context.inObject();

        // Assert
        assertFalse(result);
    }

    @Test
    public void testInObjectReturnsFalseWhenTypeIsArray() throws Exception {
        // Arrange
        JsonStreamContext context = new JsonStreamContext(1, 0) {
            @Override
            public String getCurrentName() {
                return null;
            }

            @Override
            public JsonStreamContext getParent() {
                return null;
            }
        };

        // Act
        boolean result = context.inObject();

        // Assert
        assertFalse(result);
    }
}
