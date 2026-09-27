package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonPointermatchesTest {
    @Test
    public void testMatchesWhenNextSegmentIsNull() {
        JsonPointer pointer = new JsonPointer();
        assertTrue(pointer.matches());
    }

    @Test
    public void testMatchesWhenNextSegmentIsNotNull() throws Exception {
        JsonPointer next = new JsonPointer();
        JsonPointer pointer = new JsonPointer("test", "segment", next);
        assertFalse(pointer.matches());
    }
}
