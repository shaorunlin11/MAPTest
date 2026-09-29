package com.fasterxml.jackson.core.io;

import org.junit.Test;
import org.junit.Assert;
import com.fasterxml.jackson.core.util.BufferRecycler;

public class IOContextreleaseNameCopyBufferTest {
    @Test
    public void testReleaseNameCopyBuffer() throws Exception {
        // Create a BufferRecycler instance
        BufferRecycler bufferRecycler = new BufferRecycler();

        // Create an IOContext instance
        IOContext ioContext = new IOContext(bufferRecycler, new Object(), false);

        // Set the _nameCopyBuffer to a non-null value
        char[] nameCopyBuffer = new char[10];
        ioContext._nameCopyBuffer = nameCopyBuffer;

        // Call the method under test
        ioContext.releaseNameCopyBuffer(nameCopyBuffer);

        // Verify that _nameCopyBuffer is now null
        Assert.assertNull(ioContext._nameCopyBuffer);
    }
}
