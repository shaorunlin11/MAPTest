package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonLocationgetCharOffsetTest {
    @Test
    public void testGetCharOffset() throws Exception {
        // Test with constructor that takes totalChars
        JsonLocation location1 = new JsonLocation(null, 100L, 5, 3);
        assertEquals(100L, location1.getCharOffset());

        // Test with constructor that takes totalBytes and totalChars
        JsonLocation location2 = new JsonLocation(null, 200L, 150L, 6, 4);
        assertEquals(150L, location2.getCharOffset());

        // Test with NA instance
        assertEquals(-1L, JsonLocation.NA.getCharOffset());
    }
}
