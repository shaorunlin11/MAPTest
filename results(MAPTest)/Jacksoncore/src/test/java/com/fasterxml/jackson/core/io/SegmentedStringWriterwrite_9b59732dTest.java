package com.fasterxml.jackson.core.io;

import org.junit.Test;
import org.junit.Assert;
import java.io.Writer;
import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.TextBuffer;

public class SegmentedStringWriterwrite_9b59732dTest {
    @Test
    public void testWrite() throws Exception {
        BufferRecycler bufferRecycler = new BufferRecycler();
        SegmentedStringWriter writer = new SegmentedStringWriter(bufferRecycler);

        char[] cbuf = {'t', 'e', 's', 't'};
        writer.write(cbuf);

        // Verify that the buffer contains the expected string
        // Use reflection to access private field _buffer
        java.lang.reflect.Field bufferField = SegmentedStringWriter.class.getDeclaredField("_buffer");
        bufferField.setAccessible(true);
        TextBuffer buffer = (TextBuffer) bufferField.get(writer);
        Assert.assertEquals("test", buffer.toString());
    }
}
