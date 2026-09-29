package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonStreamContextpathAsPointer_8bac234eTest {

    @Test
    public void testPathAsPointerWithIncludeRootFalse() {
        JsonStreamContext context = new JsonStreamContext(1, 0) {
            @Override
            public String getCurrentName() {
                return "dummy";
            }

            @Override
            public JsonStreamContext getParent() {
                return null;
            }
        };
        JsonPointer pointer = context.pathAsPointer(false);
        assertNotNull("Should return a non-null JsonPointer", pointer);
    }

    @Test
    public void testPathAsPointerWithIncludeRootTrue() {
        JsonStreamContext context = new JsonStreamContext(1, 0) {
            @Override
            public String getCurrentName() {
                return "dummy";
            }

            @Override
            public JsonStreamContext getParent() {
                return null;
            }
        };
        JsonPointer pointer = context.pathAsPointer(true);
        assertNotNull("Should return a non-null JsonPointer", pointer);
    }
}
