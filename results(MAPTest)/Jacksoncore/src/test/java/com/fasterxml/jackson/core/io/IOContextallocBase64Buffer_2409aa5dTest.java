package com.fasterxml.jackson.core.io;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import com.fasterxml.jackson.core.util.BufferRecycler;

public class IOContextallocBase64Buffer_2409aa5dTest {
    private IOContext ioContext;
    private BufferRecycler bufferRecycler;

    @Before
    public void setUp() {
        bufferRecycler = new BufferRecycler();
        ioContext = new IOContext(bufferRecycler, null, false);
    }

    @After
    public void tearDown() {
        ioContext = null;
        bufferRecycler = null;
    }

    @Test
    public void testAllocBase64Buffer() {
        int minSize = 1024;
        byte[] result = ioContext.allocBase64Buffer(minSize);
        Assert.assertNotNull("Result should not be null", result);
        Assert.assertTrue("Result should have length >= minSize", result.length >= minSize);
        Assert.assertArrayEquals("Result should have the same content as the assigned _base64Buffer", result, ioContext._base64Buffer);
    }
}
