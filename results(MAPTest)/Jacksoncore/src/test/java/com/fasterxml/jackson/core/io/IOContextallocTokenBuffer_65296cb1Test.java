package com.fasterxml.jackson.core.io;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import com.fasterxml.jackson.core.util.BufferRecycler;

public class IOContextallocTokenBuffer_65296cb1Test {
    private IOContext ioContext;
    private BufferRecycler bufferRecycler;

    @Before
    public void setUp() {
        bufferRecycler = new BufferRecycler();
        ioContext = new IOContext(bufferRecycler, new Object(), false);
    }

    @After
    public void tearDown() {
        ioContext = null;
        bufferRecycler = null;
    }

    @Test
    public void testAllocTokenBufferWithMinSize() {
        int minSize = 10;
        char[] result = ioContext.allocTokenBuffer(minSize);
        Assert.assertNotNull("Result should not be null", result);
        Assert.assertTrue("Result should have length >= minSize", result.length >= minSize);
        Assert.assertEquals("Result should have the same content as _tokenCBuffer", result, ioContext._tokenCBuffer);
    }
}
