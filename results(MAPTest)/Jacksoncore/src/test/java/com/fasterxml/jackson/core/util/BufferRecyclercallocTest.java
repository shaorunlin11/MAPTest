package com.fasterxml.jackson.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class BufferRecyclercallocTest {

    @Test
    public void testCallocWithPositiveSize() {
        BufferRecycler recycler = new BufferRecycler();
        char[] result = recycler.calloc(10);
        assertNotNull(result);
        assertEquals(10, result.length);
    }

    @Test
    public void testCallocWithZeroSize() {
        BufferRecycler recycler = new BufferRecycler();
        char[] result = recycler.calloc(0);
        assertNotNull(result);
        assertEquals(0, result.length);
    }

    @Test
    public void testCallocWithLargeSize() {
        BufferRecycler recycler = new BufferRecycler();
        char[] result = recycler.calloc(10000);
        assertNotNull(result);
        assertEquals(10000, result.length);
    }
}
