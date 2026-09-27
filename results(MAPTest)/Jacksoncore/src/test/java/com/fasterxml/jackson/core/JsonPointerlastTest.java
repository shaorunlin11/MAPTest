package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonPointerlastTest {

    @Test
    public void testLastOnEmptyPointer() {
        JsonPointer empty = JsonPointer.EMPTY;
        assertNull(empty.last());
    }

    @Test
    public void testLastOnSingleSegmentPointer() {
        JsonPointer pointer = new JsonPointer("test", "test", JsonPointer.EMPTY);
        assertEquals(pointer, pointer.last());
    }

    @Test
    public void testLastOnMultiSegmentPointer() {
        JsonPointer segment3 = new JsonPointer("a/b/c", "c", JsonPointer.EMPTY);
        JsonPointer segment2 = new JsonPointer("a/b", "b", segment3);
        JsonPointer segment1 = new JsonPointer("a", "a", segment2);

        assertEquals(segment3, segment1.last());
    }
}
