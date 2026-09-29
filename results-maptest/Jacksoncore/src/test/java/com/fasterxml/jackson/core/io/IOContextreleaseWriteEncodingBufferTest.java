package com.fasterxml.jackson.core.io;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import com.fasterxml.jackson.core.util.BufferRecycler;

public class IOContextreleaseWriteEncodingBufferTest {
    private IOContext ioContext;
    private BufferRecycler bufferRecycler;

    @Before
    public void setUp() throws Exception {
        bufferRecycler = new BufferRecycler();
        ioContext = new IOContext(bufferRecycler, "testSource", false);
    }

    @After
    public void tearDown() throws Exception {
        ioContext = null;
        bufferRecycler = null;
    }

    @Test
    public void testReleaseWriteEncodingBufferWithNonNullBuffer() throws Exception {
        byte[] buffer = new byte[1024];
        ioContext._writeEncodingBuffer = buffer;

        ioContext.releaseWriteEncodingBuffer(buffer);

        Assert.assertNull(ioContext._writeEncodingBuffer);
        // Verify that the buffer was passed to the buffer recycler
        // This is a structural check; actual verification would require reflection or mocking
    }

    @Test
    public void testReleaseWriteEncodingBufferWithNullBuffer() throws Exception {
        ioContext.releaseWriteEncodingBuffer(null);

        // No changes expected in internal state
        Assert.assertNull(ioContext._writeEncodingBuffer);
    }
}
