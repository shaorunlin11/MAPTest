package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonPointermatchesPropertyTest {

    @Test
    public void testMatchesPropertyWithMatchingNameAndNonNullNextSegment() {
        // Create a JsonPointer with a non-null next segment and matching property name
        JsonPointer pointer = new JsonPointer("test", "property", new JsonPointer());
        assertTrue(pointer.matchesProperty("property"));
    }

    @Test
    public void testMatchesPropertyWithNonMatchingName() {
        // Create a JsonPointer with a non-null next segment but non-matching property name
        JsonPointer pointer = new JsonPointer("test", "property", new JsonPointer());
        assertFalse(pointer.matchesProperty("otherProperty"));
    }

    @Test
    public void testMatchesPropertyWithNullNextSegment() {
        // Create a JsonPointer with a null next segment
        JsonPointer pointer = new JsonPointer();
        assertFalse(pointer.matchesProperty("property"));
    }

    @Test
    public void testMatchesPropertyWithEmptyMatchingPropertyName() {
        // Create a JsonPointer with an empty matching property name
        JsonPointer pointer = new JsonPointer();
        assertFalse(pointer.matchesProperty(""));
    }

    @Test
    public void testMatchesPropertyWithNullPropertyName() {
        // Create a JsonPointer with a non-null next segment
        JsonPointer pointer = new JsonPointer("test", "property", new JsonPointer());
        assertFalse(pointer.matchesProperty(null));
    }
}
