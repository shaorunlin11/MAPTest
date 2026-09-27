package com.fasterxml.jackson.core.io;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.util.BufferRecycler;

public class IOContextallocReadIOBuffer_1d1a2fe8Test {
    private IOContext ioContext;
    private BufferRecycler bufferRecycler;

    @Before
    public void setUp() throws Exception {
        bufferRecycler = new BufferRecycler();
        ioContext = new IOContext(bufferRecycler, new Object(), false);
    }

    @After
    public void tearDown() throws Exception {
        ioContext = null;
        bufferRecycler = null;
    }

    @Test
    public void testAllocReadIOBuffer() {
        byte[] result = ioContext.allocReadIOBuffer();
        Assert.assertNotNull("Allocated read I/O buffer should not be null", result);
        Assert.assertTrue("Allocated read I/O buffer should be a byte array", result instanceof byte[]);
        Assert.assertEquals("Allocated read I/O buffer should have the same content as the one stored in IOContext", result, ioContext._readIOBuffer);
    }
}
