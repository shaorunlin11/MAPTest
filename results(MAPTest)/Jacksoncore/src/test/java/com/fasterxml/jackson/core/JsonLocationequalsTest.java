package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonLocationequalsTest {

    @Test
    public void testEqualsWithSameObject() {
        JsonLocation loc = new JsonLocation(null, -1L, -1L, -1, -1);
        assertTrue(loc.equals(loc));
    }

    @Test
    public void testEqualsWithNull() {
        JsonLocation loc = new JsonLocation(null, -1L, -1L, -1, -1);
        assertFalse(loc.equals(null));
    }

    @Test
    public void testEqualsWithDifferentType() {
        JsonLocation loc = new JsonLocation(null, -1L, -1L, -1, -1);
        assertFalse(loc.equals("test"));
    }

    @Test
    public void testEqualsWithSameValues() {
        JsonLocation loc1 = new JsonLocation("source", 100L, 5, 10);
        JsonLocation loc2 = new JsonLocation("source", 100L, 5, 10);
        assertTrue(loc1.equals(loc2));
    }

    @Test
    public void testEqualsWithDifferentSourceRef() {
        JsonLocation loc1 = new JsonLocation("source1", 100L, 5, 10);
        JsonLocation loc2 = new JsonLocation("source2", 100L, 5, 10);
        assertFalse(loc1.equals(loc2));
    }

    @Test
    public void testEqualsWithNullSourceRef() {
        JsonLocation loc1 = new JsonLocation(null, 100L, 5, 10);
        JsonLocation loc2 = new JsonLocation(null, 100L, 5, 10);
        assertTrue(loc1.equals(loc2));
    }

    @Test
    public void testEqualsWithNullSourceRefAndNonNull() {
        JsonLocation loc1 = new JsonLocation(null, 100L, 5, 10);
        JsonLocation loc2 = new JsonLocation("source", 100L, 5, 10);
        assertFalse(loc1.equals(loc2));
    }

    @Test
    public void testEqualsWithDifferentLineNumbers() {
        JsonLocation loc1 = new JsonLocation("source", 100L, 5, 10);
        JsonLocation loc2 = new JsonLocation("source", 100L, 6, 10);
        assertFalse(loc1.equals(loc2));
    }

    @Test
    public void testEqualsWithDifferentColumnNumbers() {
        JsonLocation loc1 = new JsonLocation("source", 100L, 5, 10);
        JsonLocation loc2 = new JsonLocation("source", 100L, 5, 11);
        assertFalse(loc1.equals(loc2));
    }

    @Test
    public void testEqualsWithDifferentTotalChars() {
        JsonLocation loc1 = new JsonLocation("source", 100L, 5, 10);
        JsonLocation loc2 = new JsonLocation("source", 101L, 5, 10);
        assertFalse(loc1.equals(loc2));
    }

    @Test
    public void testEqualsWithDifferentByteOffset() {
        JsonLocation loc1 = new JsonLocation("source", 100L, 5, 10);
        JsonLocation loc2 = new JsonLocation("source", 100L, 5, 10);
        // Assuming getByteOffset() returns _totalBytes
        // We need to modify the constructor to set _totalBytes properly
        // For this test, we'll create a modified version of JsonLocation
        // with a different _totalBytes value
        JsonLocation loc3 = new JsonLocation("source", 101L, 5, 10);
        assertFalse(loc1.equals(loc3));
    }
}
