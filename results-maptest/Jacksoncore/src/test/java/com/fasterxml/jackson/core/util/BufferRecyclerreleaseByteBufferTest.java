package com.fasterxml.jackson.core.util;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class BufferRecyclerreleaseByteBufferTest {
    private BufferRecycler bufferRecycler;
    private byte[] testBuffer;

    @Before
    public void setUp() {
        bufferRecycler = new BufferRecycler(4, 4);
        testBuffer = new byte[8000];
    }

    @Test
    public void testReleaseByteBufferAssignsBufferToCorrectIndex() {
        int index = BufferRecycler.BYTE_READ_IO_BUFFER;
        bufferRecycler.releaseByteBuffer(index, testBuffer);
        assertSame("Buffer should be assigned to the correct index", testBuffer, bufferRecycler._byteBuffers[index]);
    }
}
