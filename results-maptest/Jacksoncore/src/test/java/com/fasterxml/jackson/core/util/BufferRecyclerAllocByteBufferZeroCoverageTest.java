package com.fasterxml.jackson.core.util;

import org.junit.Test;

public class BufferRecyclerAllocByteBufferZeroCoverageTest {
    @Test
    public void testAllocByteBufferWithTargetLines() {
        BufferRecycler recycler = new BufferRecycler(1, 0);
        int ix = 0;
        int minSize = 10;

        // Ensure _byteBuffers[ix] is not null and has length >= minSize
        recycler._byteBuffers[ix] = new byte[15];

        // Call the method to execute target lines
        recycler.allocByteBuffer(ix, minSize);
    }
}
