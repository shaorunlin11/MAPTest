package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonPointermatchesElementTest {

    @Test
    public void testMatchesElementWithMatchingIndexAndNonNegative() {
        JsonPointer pointer = new JsonPointer("test", "0", 0, null);
        assertTrue(pointer.matchesElement(0));
    }

    @Test
    public void testMatchesElementWithNonMatchingIndex() {
        JsonPointer pointer = new JsonPointer("test", "1", 1, null);
        assertFalse(pointer.matchesElement(0));
    }

    @Test
    public void testMatchesElementWithNegativeIndex() {
        JsonPointer pointer = new JsonPointer("test", "0", 0, null);
        assertFalse(pointer.matchesElement(-1));
    }

    @Test
    public void testMatchesElementWithEmptyPointer() {
        JsonPointer pointer = JsonPointer.EMPTY;
        assertFalse(pointer.matchesElement(0));
    }
}
