package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonStreamContextgetStartLocationTest {
    @Test
    public void testGetStartLocation_returnsNA() {
        JsonStreamContext context = new JsonStreamContext() {
            public String getCurrentName() {
                return null;
            }

            public JsonStreamContext getParent() {
                return null;
            }

            public int getRowNumber() {
                return 0;
            }

            public int getColumnNumber() {
                return 0;
            }
        };
        Object srcRef = new Object();
        JsonLocation result = context.getStartLocation(srcRef);
        assertEquals(JsonLocation.NA, result);
    }
}
