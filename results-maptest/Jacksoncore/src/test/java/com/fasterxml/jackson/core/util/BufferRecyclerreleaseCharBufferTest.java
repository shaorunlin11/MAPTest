package com.fasterxml.jackson.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class BufferRecyclerreleaseCharBufferTest {
    @Test
    public void testReleaseCharBuffer() throws Exception {
        // Create a BufferRecycler instance with 4 char buffers
        BufferRecycler recycler = new BufferRecycler(4, 4);

        // Create a char buffer to release
        char[] buffer = new char[100];

        // Release the buffer at index 0
        recycler.releaseCharBuffer(0, buffer);

        // Verify that the buffer was stored at index 0
        assertSame("Buffer should be stored at index 0", buffer, recycler._charBuffers[0]);

        // Verify that other indices are not modified
        assertNull("Index 1 should remain null", recycler._charBuffers[1]);
        assertNull("Index 2 should remain null", recycler._charBuffers[2]);
        assertNull("Index 3 should remain null", recycler._charBuffers[3]);
    }

    @Test
    public void testReleaseCharBufferWithInvalidIndex() throws Exception {
        // Create a BufferRecycler instance with 4 char buffers
        BufferRecycler recycler = new BufferRecycler(4, 4);

        // Create a char buffer to release
        char[] buffer = new char[100];

        // Try to release the buffer at an invalid index (4)
        try {
            recycler.releaseCharBuffer(4, buffer);
            fail("Expected ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException e) {
            // Expected exception
        }
    }

    @Test
    public void testReleaseCharBufferWithNullBuffer() throws Exception {
        // Create a BufferRecycler instance with 4 char buffers
        BufferRecycler recycler = new BufferRecycler(4, 4);

        // Try to release a null buffer
        recycler.releaseCharBuffer(0, null);

        // Verify that the buffer was not stored
        assertNull("Null buffer should not be stored", recycler._charBuffers[0]);
    }
}
