package com.fasterxml.jackson.core.io;

import org.junit.Test;

import com.fasterxml.jackson.core.util.BufferRecycler;

public class IOContextAllocWriteEncodingBufferZeroCoverageTest {
    @Test
    public void testAllocWriteEncodingBuffer() {
        BufferRecycler bufferRecycler = new BufferRecycler();
        IOContext ioContext = new IOContext(bufferRecycler, new Object(), false);

        // Call the method under test
        ioContext.allocWriteEncodingBuffer();
    }
}
