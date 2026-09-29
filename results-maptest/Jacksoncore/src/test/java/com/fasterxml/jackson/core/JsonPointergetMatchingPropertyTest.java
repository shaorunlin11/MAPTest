package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonPointergetMatchingPropertyTest {
    @Test
    public void testGetMatchingProperty() throws Exception {
        // Test case 1: Default constructor
        JsonPointer defaultPointer = new JsonPointer();
        assertEquals("", defaultPointer.getMatchingProperty());

        // Test case 2: Constructor with segment and next
        JsonPointer segmentPointer = new JsonPointer("test", "property", null);
        assertEquals("property", segmentPointer.getMatchingProperty());

        // Test case 3: Constructor with segment, index, and next
        JsonPointer indexedPointer = new JsonPointer("test", "array[0]", 0, null);
        assertEquals("array[0]", indexedPointer.getMatchingProperty());
    }
}
