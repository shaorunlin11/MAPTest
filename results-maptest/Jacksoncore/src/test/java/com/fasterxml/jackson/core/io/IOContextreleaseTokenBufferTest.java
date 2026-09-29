package com.fasterxml.jackson.core.io;

import org.junit.Test;
import org.junit.Assert;
import com.fasterxml.jackson.core.util.BufferRecycler;

public class IOContextreleaseTokenBufferTest {
    @Test
    public void testReleaseTokenBuffer() throws Exception {
        BufferRecycler bufferRecycler = new BufferRecycler();
        Object sourceRef = new Object();
        boolean managedResource = false;
        IOContext ioContext = new IOContext(bufferRecycler, sourceRef, managedResource);

        char[] tokenBuffer = new char[10];
        ioContext._tokenCBuffer = tokenBuffer;

        ioContext.releaseTokenBuffer(tokenBuffer);

        Assert.assertNull(ioContext._tokenCBuffer);
    }

    @Test
    public void testReleaseTokenBufferWithNullBuffer() throws Exception {
        BufferRecycler bufferRecycler = new BufferRecycler();
        Object sourceRef = new Object();
        boolean managedResource = false;
        IOContext ioContext = new IOContext(bufferRecycler, sourceRef, managedResource);

        ioContext._tokenCBuffer = new char[10];

        ioContext.releaseTokenBuffer(null);

        Assert.assertNotNull(ioContext._tokenCBuffer);
    }
}
