package com.fasterxml.jackson.core.io;

import java.io.Writer;
import org.junit.Test;
import org.junit.Assert;
import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.TextBuffer;

public class SegmentedStringWriterappend_0b062a8bTest {
    @Test
    public void testAppend() throws Exception {
        BufferRecycler bufferRecycler = new BufferRecycler();
        SegmentedStringWriter writer = new SegmentedStringWriter(bufferRecycler);
        Writer result = writer.append('a');
        Assert.assertSame(writer, result);
    }
}
