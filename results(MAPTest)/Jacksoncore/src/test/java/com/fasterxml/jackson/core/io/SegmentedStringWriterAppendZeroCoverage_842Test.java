package com.fasterxml.jackson.core.io;

import org.junit.Test;

import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.TextBuffer;


public class SegmentedStringWriterAppendZeroCoverage_842Test {
    @Test
    public void testAppendWithValidParameters() throws Exception {
        BufferRecycler bufferRecycler = new BufferRecycler();
        TextBuffer buffer = new TextBuffer(bufferRecycler);
        SegmentedStringWriter writer = new SegmentedStringWriter(bufferRecycler);

        CharSequence csq = "Hello, World!";
        int start = 0;
        int end = 5;

        writer.append(csq, start, end);
    }
}
