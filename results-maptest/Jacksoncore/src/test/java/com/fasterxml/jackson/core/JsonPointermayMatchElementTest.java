package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonPointermayMatchElementTest {
    @Test
    public void testMayMatchElementWithPositiveIndex() throws Exception {
        // Create a JsonPointer with a positive index
        JsonPointer pointer = new JsonPointer("test", "element", 5, null);
        assertTrue(pointer.mayMatchElement());
    }

    @Test
    public void testMayMatchElementWithNegativeIndex() throws Exception {
        // Create a JsonPointer with a negative index
        JsonPointer pointer = new JsonPointer("test", "element", -1, null);
        assertFalse(pointer.mayMatchElement());
    }

    @Test
    public void testMayMatchElementWithZeroIndex() throws Exception {
        // Create a JsonPointer with a zero index
        JsonPointer pointer = new JsonPointer("test", "element", 0, null);
        assertTrue(pointer.mayMatchElement());
    }

    @Test
    public void testMayMatchElementWithDefaultConstructor() throws Exception {
        // Create a JsonPointer using the default constructor
        JsonPointer pointer = new JsonPointer();
        assertFalse(pointer.mayMatchElement());
    }
}
