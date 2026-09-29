package com.fasterxml.jackson.core.io;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
import com.fasterxml.jackson.core.util.BufferRecycler;

public class IOContextallocWriteEncodingBuffer_54e6af07Test {
    private IOContext ioContext;
    private BufferRecycler bufferRecycler;

    @Before
    public void setUp() throws Exception {
        bufferRecycler = new BufferRecycler();
        ioContext = new IOContext(bufferRecycler, new Object(), false);
    }

    @Test
    public void testAllocWriteEncodingBufferWithMinSize() throws Exception {
        int minSize = 1024;
        byte[] result = ioContext.allocWriteEncodingBuffer(minSize);
        Assert.assertNotNull("Result should not be null", result);
        Assert.assertTrue("Result should have at least the requested size", result.length >= minSize);
        Assert.assertSame("Should return the same buffer from bufferRecycler", result, ioContext._writeEncodingBuffer);
    }
}
