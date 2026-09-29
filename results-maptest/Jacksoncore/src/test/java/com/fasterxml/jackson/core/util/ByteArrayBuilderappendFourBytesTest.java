package com.fasterxml.jackson.core.util;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.util.LinkedList;
public class ByteArrayBuilderappendFourBytesTest {
    private ByteArrayBuilder builder;
    private BufferRecycler bufferRecycler;

    @Before
    public void setUp() {
        bufferRecycler = new BufferRecycler();
        builder = new ByteArrayBuilder(bufferRecycler, 500);
    }

    @After
    public void tearDown() {
        builder = null;
        bufferRecycler = null;
    }

    @Test
    public void testAppendFourBytesWithSpaceInCurrentBlock() throws Exception {
        int b32 = 0x12345678;
        builder.appendFourBytes(b32);

        byte[] result = builder.toByteArray();
        Assert.assertArrayEquals(new byte[]{(byte) 0x12, (byte) 0x34, (byte) 0x56, (byte) 0x78}, result);
    }
}
