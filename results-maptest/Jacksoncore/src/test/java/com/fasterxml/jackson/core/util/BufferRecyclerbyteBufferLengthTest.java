package com.fasterxml.jackson.core.util;

import org.junit.Test;
import org.junit.Assert;

public class BufferRecyclerbyteBufferLengthTest {
    @Test
    public void testByteBufferLengthForValidIndices() {
        BufferRecycler recycler = new BufferRecycler();
        Assert.assertEquals(8000, recycler.byteBufferLength(BufferRecycler.BYTE_READ_IO_BUFFER));
        Assert.assertEquals(8000, recycler.byteBufferLength(BufferRecycler.BYTE_WRITE_ENCODING_BUFFER));
        Assert.assertEquals(2000, recycler.byteBufferLength(BufferRecycler.BYTE_WRITE_CONCAT_BUFFER));
        Assert.assertEquals(2000, recycler.byteBufferLength(BufferRecycler.BYTE_BASE64_CODEC_BUFFER));
    }
}
