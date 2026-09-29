package com.fasterxml.jackson.core.io;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
import com.fasterxml.jackson.core.util.BufferRecycler;

public class IOContextallocReadIOBuffer_7fd47e21Test {
    private IOContext ioContext;
    private BufferRecycler bufferRecycler;

    @Before
    public void setUp() {
        bufferRecycler = new BufferRecycler();
        ioContext = new IOContext(bufferRecycler, new Object(), false);
    }

    @Test
    public void testAllocReadIOBufferWithMinSize() {
        int minSize = 1024;
        byte[] buffer = ioContext.allocReadIOBuffer(minSize);

        Assert.assertNotNull("Buffer should not be null", buffer);
        Assert.assertTrue("Buffer size should be at least " + minSize, buffer.length >= minSize);
        Assert.assertSame("Buffer should be the same as the one assigned to _readIOBuffer", buffer, ioContext._readIOBuffer);
    }
}
