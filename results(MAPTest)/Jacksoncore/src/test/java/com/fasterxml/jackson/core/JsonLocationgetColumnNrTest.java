package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonLocationgetColumnNrTest {
    @Test
    public void testGetColumnNr() throws Exception {
        // Create a JsonLocation instance with known column number
        JsonLocation location = new JsonLocation(null, 0L, 0L, 5, 10);

        // Verify that getColumnNr returns the expected value
        assertEquals(10, location.getColumnNr());
    }
}
