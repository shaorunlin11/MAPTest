package com.fasterxml.jackson.core.io;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import com.fasterxml.jackson.core.util.BufferRecycler;

public class IOContextreleaseReadIOBufferTest {
    private IOContext ioContext;
    private BufferRecycler bufferRecycler;

    @Before
    public void setUp() throws Exception {
        bufferRecycler = new BufferRecycler();
        ioContext = new IOContext(bufferRecycler, new Object(), false);
    }

    @Test
    public void testReleaseReadIOBufferWithNonNullBuffer() throws Exception {
        byte[] buffer = new byte[1024];
        ioContext._readIOBuffer = buffer;

        ioContext.releaseReadIOBuffer(buffer);

        Assert.assertNull(ioContext._readIOBuffer);
    }

    @Test
    public void testReleaseReadIOBufferWithNullBuffer() throws Exception {
        ioContext.releaseReadIOBuffer(null);

        Assert.assertNull(ioContext._readIOBuffer);
    }
}
