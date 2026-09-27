package com.fasterxml.jackson.core.util;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class BufferRecyclerallocCharBuffer_79d0598bTest {
    private BufferRecycler bufferRecycler;

    @Before
    public void setUp() {
        bufferRecycler = new BufferRecycler();
    }

    @After
    public void tearDown() {
        bufferRecycler = null;
    }

    @Test
    public void testAllocCharBuffer() {
        int CHAR_TOKEN_BUFFER = 0;
        int CHAR_CONCAT_BUFFER = 1;
        int CHAR_TEXT_BUFFER = 2;
        int CHAR_NAME_COPY_BUFFER = 3;
        int[] validIndices = { CHAR_TOKEN_BUFFER, CHAR_CONCAT_BUFFER, CHAR_TEXT_BUFFER, CHAR_NAME_COPY_BUFFER };
        int[] CHAR_BUFFER_LENGTHS = new int[] { 4000, 4000, 200, 200 };
        for (int ix : validIndices) {
            char[] buffer = bufferRecycler.allocCharBuffer(ix);
            Assert.assertNotNull("Buffer should not be null", buffer);
            Assert.assertTrue("Buffer size should match expected length", buffer.length == CHAR_BUFFER_LENGTHS[ix]);
        }
    }
}
