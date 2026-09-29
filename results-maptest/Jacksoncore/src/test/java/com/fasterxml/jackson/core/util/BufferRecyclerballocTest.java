package com.fasterxml.jackson.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class BufferRecyclerballocTest {
    @Test
    public void testBallocWithPositiveSize() {
        BufferRecycler recycler = new BufferRecycler();
        byte[] result = recycler.balloc(10);
        assertNotNull(result);
        assertEquals(10, result.length);
    }

    @Test
    public void testBallocWithZeroSize() {
        BufferRecycler recycler = new BufferRecycler();
        byte[] result = recycler.balloc(0);
        assertNotNull(result);
        assertEquals(0, result.length);
    }

    @Test
    public void testBallocWithLargeSize() {
        BufferRecycler recycler = new BufferRecycler();
        byte[] result = recycler.balloc(10000);
        assertNotNull(result);
        assertEquals(10000, result.length);
    }
}
