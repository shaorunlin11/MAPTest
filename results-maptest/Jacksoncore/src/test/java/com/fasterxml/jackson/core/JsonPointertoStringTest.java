package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonPointertoStringTest {
    @Test
    public void testToString_returnsPrecomputedString() throws Exception {
        // Create a JsonPointer instance with a known _asString value
        JsonPointer pointer = new JsonPointer("/a/b/c", "c", new JsonPointer());

        // Verify that the toString method returns the expected value
        assertEquals("/a/b/c", pointer.toString());
    }

    @Test
    public void testToString_forEmptyPointer_returnsEmptyString() throws Exception {
        // Use the EMPTY static field which is initialized with an empty string
        assertEquals("", JsonPointer.EMPTY.toString());
    }

    @Test
    public void testToString_forPointerWithIndex_returnsCorrectString() throws Exception {
        // Create a JsonPointer with a specific index
        JsonPointer pointer = new JsonPointer("/a/1", "1", 1, new JsonPointer());

        // Verify that the toString method returns the expected value
        assertEquals("/a/1", pointer.toString());
    }
}
