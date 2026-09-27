package com.fasterxml.jackson.core.io;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import com.fasterxml.jackson.core.util.BufferRecycler;

public class IOContextallocTokenBuffer_90e79c8fTest {
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
    public void testAllocTokenBuffer() {
        char[] result = ioContext.allocTokenBuffer();
        Assert.assertNotNull("allocTokenBuffer should return a non-null char array", result);
        Assert.assertTrue("The returned buffer size should be at least the CHAR_TOKEN_BUFFER size", result.length >= BufferRecycler.CHAR_TOKEN_BUFFER);
    }
}
