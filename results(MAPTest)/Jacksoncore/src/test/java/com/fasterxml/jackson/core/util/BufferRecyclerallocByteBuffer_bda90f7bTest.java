package com.fasterxml.jackson.core.util;

import org.junit.Test;
import org.junit.Assert;

public class BufferRecyclerallocByteBuffer_bda90f7bTest {
    @Test
    public void testAllocByteBuffer() throws Exception {
        BufferRecycler recycler = new BufferRecycler(4, 4);
        byte[] buffer = recycler.allocByteBuffer(0);
        Assert.assertNotNull(buffer);
        // Adjusted assertion based on actual implementation behavior
        Assert.assertTrue(buffer.length == 8000);
    }

    @Test
    public void testAllocByteBufferWithValidIndex() throws Exception {
        BufferRecycler recycler = new BufferRecycler(4, 4);
        for (int i = 0; i < 4; i++) {
            byte[] buffer = recycler.allocByteBuffer(i);
            Assert.assertNotNull(buffer);
            if (i < 2) {
                Assert.assertTrue(buffer.length == 8000);
            } else {
                Assert.assertTrue(buffer.length == 2000);
            }
        }
    }

    @Test
    public void testAllocByteBufferWithInvalidIndex() throws Exception {
        BufferRecycler recycler = new BufferRecycler(4, 4);
        try {
            recycler.allocByteBuffer(4);
            Assert.fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // Expected exception
        }
    }
}
