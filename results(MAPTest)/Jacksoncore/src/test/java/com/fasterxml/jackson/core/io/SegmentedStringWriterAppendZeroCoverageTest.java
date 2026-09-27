package com.fasterxml.jackson.core.io;

import org.junit.Test;

import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.TextBuffer;


public class SegmentedStringWriterAppendZeroCoverageTest {
    @Test
    public void testAppend() throws Exception {
        BufferRecycler bufferRecycler = new BufferRecycler();
        TextBuffer buffer = new TextBuffer(bufferRecycler);
        SegmentedStringWriter writer = new SegmentedStringWriter(bufferRecycler);

        CharSequence csq = "test";
        writer.append(csq);
    }
}
