package com.fasterxml.jackson.core.io;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
import com.fasterxml.jackson.core.util.BufferRecycler;

public class IOContextallocNameCopyBufferTest {
    private IOContext ioContext;
    private BufferRecycler bufferRecycler;

    @Before
    public void setUp() throws Exception {
        bufferRecycler = new BufferRecycler();
        ioContext = new IOContext(bufferRecycler, new Object(), false);
    }

    @Test
    public void testAllocNameCopyBuffer() {
        int minSize = 10;
        char[] result = ioContext.allocNameCopyBuffer(minSize);
        Assert.assertNotNull("Result should not be null", result);
        Assert.assertTrue("Result should have length >= minSize", result.length >= minSize);
        Assert.assertEquals("Result should be the same as _nameCopyBuffer", result, ioContext._nameCopyBuffer);
    }
}
