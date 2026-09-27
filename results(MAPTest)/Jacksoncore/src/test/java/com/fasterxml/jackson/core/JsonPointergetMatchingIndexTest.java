package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonPointergetMatchingIndexTest {
    @Test
    public void testGetMatchingIndex() throws Exception {
        // Test case 1: Default constructor
        JsonPointer defaultPointer = new JsonPointer();
        assertEquals(-1, defaultPointer.getMatchingIndex());

        // Test case 2: Constructor with segment and next
        JsonPointer segmentPointer = new JsonPointer("test", "property", null);
        assertEquals(-1, segmentPointer.getMatchingIndex());

        // Test case 3: Constructor with segment, index, and next
        JsonPointer indexedPointer = new JsonPointer("test", "property", 42, null);
        assertEquals(42, indexedPointer.getMatchingIndex());
    }
}
