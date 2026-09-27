package com.fasterxml.jackson.core;

import org.junit.Test;
import org.junit.Assert;

public class JsonPointermatchElementTest {
    @Test
    public void testMatchElementNegativeIndex() {
        JsonPointer pointer = new JsonPointer("test", "test", -1, null);
        Assert.assertNull(pointer.matchElement(-1));
    }

    @Test
    public void testMatchElementMismatchedIndex() {
        JsonPointer pointer = new JsonPointer("test", "test", 0, null);
        Assert.assertNull(pointer.matchElement(1));
    }

    @Test
    public void testMatchElementMatchedIndex() {
        JsonPointer nextSegment = new JsonPointer();
        JsonPointer pointer = new JsonPointer("test", "test", 0, nextSegment);
        Assert.assertEquals(nextSegment, pointer.matchElement(0));
    }
}
