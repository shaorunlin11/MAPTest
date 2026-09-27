package com.fasterxml.jackson.core.io;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import com.fasterxml.jackson.core.util.BufferRecycler;

public class IOContextallocConcatBufferTest {
    private IOContext context;
    private BufferRecycler bufferRecycler;

    @Before
    public void setUp() {
        bufferRecycler = new BufferRecycler();
        context = new IOContext(bufferRecycler, "testSource", false);
    }

    @Test
    public void testAllocConcatBuffer() {
        char[] result = context.allocConcatBuffer();
        Assert.assertNotNull("Allocated buffer should not be null", result);
        Assert.assertTrue("Allocated buffer should have non-zero length", result.length > 0);
        Assert.assertEquals("Allocated buffer should be the same as the one stored in _concatCBuffer", result, context._concatCBuffer);
    }
}
