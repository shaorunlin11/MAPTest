package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonLocationgetByteOffsetTest {
    @Test
    public void testGetByteOffset() throws Exception {
        // Create a JsonLocation instance with known values
        JsonLocation location = new JsonLocation("testSource", 100L, 50L, 5, 10);

        // Verify that getByteOffset returns the expected value
        assertEquals(100L, location.getByteOffset());
    }
}
