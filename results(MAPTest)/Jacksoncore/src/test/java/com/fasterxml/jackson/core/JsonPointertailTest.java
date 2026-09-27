package com.fasterxml.jackson.core;

import org.junit.Test;
import org.junit.Assert;

public class JsonPointertailTest {
    @Test
    public void testTailReturnsNextSegment() throws Exception {
        // Create a JsonPointer with a next segment
        JsonPointer nextSegment = new JsonPointer();
        JsonPointer head = new JsonPointer("test", "segment", nextSegment);

        // Verify that tail() returns the next segment
        Assert.assertEquals(nextSegment, head.tail());
    }

    @Test
    public void testTailReturnsNullWhenNoNextSegment() throws Exception {
        // Create a JsonPointer with no next segment
        JsonPointer head = new JsonPointer();

        // Verify that tail() returns null
        Assert.assertNull(head.tail());
    }
}
