package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonProcessingExceptionclearLocationTest {
    @Test
    public void testClearLocation() throws Exception {
        // Create a JsonProcessingException with a location
        JsonLocation location = new JsonLocation(null, 1L, 1, 1);
        JsonProcessingException exception = new JsonProcessingException("Test message", location);

        // Verify that the location is not null
        assertNotNull(exception._location);

        // Call clearLocation()
        exception.clearLocation();

        // Verify that the location is now null
        assertNull(exception._location);
    }
}
