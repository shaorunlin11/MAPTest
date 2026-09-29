package com.fasterxml.jackson.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class BufferRecyclercharBufferLengthTest {
    @Test
    public void testCharBufferLength() {
        BufferRecycler recycler = new BufferRecycler();
        assertEquals(4000, recycler.charBufferLength(0));
        assertEquals(4000, recycler.charBufferLength(1));
        assertEquals(200, recycler.charBufferLength(2));
        assertEquals(200, recycler.charBufferLength(3));
    }
}
