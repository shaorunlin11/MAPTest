package com.fasterxml.jackson.core.util;

import org.junit.Test;

public class BufferRecyclerAllocCharBufferZeroCoverageTest {
    @Test
    public void testAllocCharBufferWithMinSizeLessThanDefSize() {
        BufferRecycler recycler = new BufferRecycler(1, 1);
        int ix = BufferRecycler.CHAR_TOKEN_BUFFER;
        int minSize = 10;

        // Set _charBuffers[ix] to null or a buffer with length less than minSize
        recycler._charBuffers[ix] = new char[5];

        // Call the method under test
        char[] buffer = recycler.allocCharBuffer(ix, minSize);

        // Verify that the buffer is allocated with minSize or larger
        assert buffer.length >= minSize;
    }

@Test
    public void testAllocCharBufferWithMinSizeGreaterThanOrEqualToDefSize() {
        BufferRecycler recycler = new BufferRecycler(1, 1);
        int ix = BufferRecycler.CHAR_TOKEN_BUFFER;
        int minSize = 20;

        // Set _charBuffers[ix] to a buffer with length >= minSize
        recycler._charBuffers[ix] = new char[minSize];

        // Call the method under test
        char[] buffer = recycler.allocCharBuffer(ix, minSize);

        // Verify that the buffer is returned without allocation
        assert buffer.length >= minSize;
    }

@Test
    public void testAllocCharBufferWithNullBufferAndMinSizeGreaterThanDefSize() {
        BufferRecycler recycler = new BufferRecycler(1, 1);
        int ix = BufferRecycler.CHAR_TOKEN_BUFFER;
        int minSize = 15;

        // Ensure _charBuffers[ix] is null
        recycler._charBuffers[ix] = null;

        // Call the method under test
        char[] buffer = recycler.allocCharBuffer(ix, minSize);

        // Verify that the buffer is allocated with minSize or larger
        assert buffer.length >= minSize;
    }
}
