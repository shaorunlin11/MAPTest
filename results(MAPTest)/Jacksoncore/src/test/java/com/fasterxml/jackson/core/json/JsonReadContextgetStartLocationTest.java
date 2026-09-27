package com.fasterxml.jackson.core.json;

import org.junit.Test;
import static org.junit.Assert.*;

import com.fasterxml.jackson.core.JsonLocation;

public class JsonReadContextgetStartLocationTest {

    @Test
    public void testGetStartLocation() throws Exception {
        // Create a JsonReadContext instance with known line and column numbers
        JsonReadContext context = new JsonReadContext(null, null, 0, 10, 5);

        // Call the method under test
        JsonLocation location = context.getStartLocation("testSource");

        // Verify that the returned JsonLocation has the expected properties
        assertEquals("testSource", location.getSourceRef());
        assertEquals(-1L, location.getCharOffset());
        assertEquals(10, location.getLineNr());
        assertEquals(5, location.getColumnNr());
    }
}
