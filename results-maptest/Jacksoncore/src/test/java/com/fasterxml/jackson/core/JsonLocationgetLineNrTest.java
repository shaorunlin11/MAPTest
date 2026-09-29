package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonLocationgetLineNrTest {
    @Test
    public void testGetLineNr() throws Exception {
        // Test with valid line number
        JsonLocation location1 = new JsonLocation(null, 0L, 0L, 5, 10);
        assertEquals("Should return the correct line number", 5, location1.getLineNr());

        // Test with line number set to -1 (as in NA)
        JsonLocation location2 = JsonLocation.NA;
        assertEquals("Should return -1 for NA instance", -1, location2.getLineNr());

        // Test with different line numbers
        JsonLocation location3 = new JsonLocation(null, 0L, 0L, 123, 456);
        assertEquals("Should return the line number set during construction", 123, location3.getLineNr());
    }
}
