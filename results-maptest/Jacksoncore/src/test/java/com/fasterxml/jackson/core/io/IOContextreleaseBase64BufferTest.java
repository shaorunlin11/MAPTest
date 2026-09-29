package com.fasterxml.jackson.core.io;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import com.fasterxml.jackson.core.util.BufferRecycler;

public class IOContextreleaseBase64BufferTest {
    private IOContext context;
    private BufferRecycler bufferRecycler;
    private byte[] base64Buffer;

    @Before
    public void setUp() throws Exception {
        bufferRecycler = new BufferRecycler();
        base64Buffer = new byte[1024];
        context = new IOContext(bufferRecycler, "test", true);
        context._base64Buffer = base64Buffer;
    }

    @After
    public void tearDown() throws Exception {
        context = null;
        bufferRecycler = null;
        base64Buffer = null;
    }

    @Test
    public void testReleaseBase64BufferWithNonNullBuffer() throws Exception {
        // Act
        context.releaseBase64Buffer(base64Buffer);

        // Assert
        Assert.assertNull(context._base64Buffer);
        // Verify that the buffer was released through the recycler
        // Since we can't directly verify the internal state of BufferRecycler,
        // we assume it behaves correctly based on its implementation
    }

    @Test
    public void testReleaseBase64BufferWithNullBuffer() throws Exception {
        // Act
        context.releaseBase64Buffer(null);

        // Assert
        // No operations should occur
        Assert.assertSame(base64Buffer, context._base64Buffer);
    }
}
