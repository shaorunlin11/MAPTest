package com.fasterxml.jackson.core.io;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import com.fasterxml.jackson.core.util.BufferRecycler;

public class IOContextreleaseConcatBufferTest {
    private IOContext context;
    private BufferRecycler bufferRecycler;

    @Before
    public void setUp() throws Exception {
        bufferRecycler = new BufferRecycler();
        context = new IOContext(bufferRecycler, new Object(), false);
    }

    @After
    public void tearDown() throws Exception {
        context = null;
        bufferRecycler = null;
    }

    @Test
    public void testReleaseConcatBufferWithNonNullBuffer() throws Exception {
        char[] buffer = new char[10];
        context._concatCBuffer = buffer;

        context.releaseConcatBuffer(buffer);

        Assert.assertNull(context._concatCBuffer);
    }

    @Test
    public void testReleaseConcatBufferWithNullBuffer() throws Exception {
        char[] buffer = null;
        context._concatCBuffer = new char[10];

        context.releaseConcatBuffer(buffer);

        Assert.assertNotNull(context._concatCBuffer);
    }
}
