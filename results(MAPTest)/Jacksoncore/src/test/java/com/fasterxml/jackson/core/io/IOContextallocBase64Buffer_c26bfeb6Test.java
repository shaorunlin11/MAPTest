package com.fasterxml.jackson.core.io;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import com.fasterxml.jackson.core.util.BufferRecycler;

public class IOContextallocBase64Buffer_c26bfeb6Test {
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
    public void testAllocBase64Buffer() throws Exception {
        byte[] result = ioContext.allocBase64Buffer();
        Assert.assertNotNull("Allocated base64 buffer should not be null", result);
        Assert.assertTrue("Allocated base64 buffer should be of appropriate size", result.length >= BufferRecycler.BYTE_BASE64_CODEC_BUFFER);
        Assert.assertSame("Returned buffer should be the same as the one stored in _base64Buffer", result, ioContext._base64Buffer);
    }
}
