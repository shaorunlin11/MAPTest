package com.fasterxml.jackson.core.io;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.io.Writer;
import java.io.StringWriter;
import java.io.StringReader;
import java.io.Reader;
import java.io.IOException;

import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.TextBuffer;

public class SegmentedStringWriterwrite_cb16ddacTest {
    private SegmentedStringWriter writer;
    private TextBuffer buffer;

    @Before
    public void setUp() throws Exception {
        BufferRecycler recycler = new BufferRecycler();
        writer = new SegmentedStringWriter(recycler);
        // Use reflection to access private _buffer field
        java.lang.reflect.Field bufferField = SegmentedStringWriter.class.getDeclaredField("_buffer");
        bufferField.setAccessible(true);
        buffer = (TextBuffer) bufferField.get(writer);
    }

    @After
    public void tearDown() throws Exception {
        writer = null;
        buffer = null;
    }

    @Test
    public void testWriteCharArray() throws Exception {
        char[] input = {'a', 'b', 'c'};
        writer.write(input);

        // Verify that the entire array was appended to the buffer
        Assert.assertEquals("abc", buffer.toString());
    }
}
