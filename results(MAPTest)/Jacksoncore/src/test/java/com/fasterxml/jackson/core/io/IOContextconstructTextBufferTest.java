package com.fasterxml.jackson.core.io;

import org.junit.Test;
import org.junit.Assert;

import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.TextBuffer;

public class IOContextconstructTextBufferTest {
    @Test
    public void testConstructTextBuffer() throws Exception {
        BufferRecycler bufferRecycler = new BufferRecycler();
        IOContext ioContext = new IOContext(bufferRecycler, new Object(), false);

        TextBuffer result = ioContext.constructTextBuffer();

        Assert.assertNotNull(result);
        Assert.assertTrue(result instanceof TextBuffer);
    }
}
