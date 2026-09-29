package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonLocationgetSourceRefTest {
    @Test
    public void testGetSourceRef() throws Exception {
        // Test with non-null sourceRef
        Object testSourceRef = new Object();
        JsonLocation location1 = new JsonLocation(testSourceRef, -1L, -1L, -1, -1);
        assertEquals("Should return the sourceRef object", testSourceRef, location1.getSourceRef());

        // Test with null sourceRef
        JsonLocation location2 = new JsonLocation(null, -1L, -1L, -1, -1);
        assertNull("Should return null when sourceRef is null", location2.getSourceRef());
    }
}
