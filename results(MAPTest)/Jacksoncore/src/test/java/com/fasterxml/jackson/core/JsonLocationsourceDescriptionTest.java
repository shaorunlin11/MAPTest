package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonLocationsourceDescriptionTest {
    @Test
    public void testSourceDescription() throws Exception {
        // Create a JsonLocation instance with known values
        Object sourceRef = "testSource";
        long totalBytes = 100L;
        long totalChars = 50L;
        int lineNr = 5;
        int columnNr = 10;

        JsonLocation location = new JsonLocation(sourceRef, totalBytes, totalChars, lineNr, columnNr);

        // Call the method under test
        String description = location.sourceDescription();

        // Verify that the description is not null and has some content
        assertNotNull(description);
        assertFalse(description.isEmpty());
    }
}
