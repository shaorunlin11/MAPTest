package com.fasterxml.jackson.core.io;

import org.junit.Test;

import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.TextBuffer;


public class SegmentedStringWriterWriteZeroCoverage_847Test {
    @Test
    public void testWriteWithNonNullCharArray() {
        BufferRecycler bufferRecycler = new BufferRecycler();
        TextBuffer textBuffer = new TextBuffer(bufferRecycler);
        SegmentedStringWriter writer = new SegmentedStringWriter(bufferRecycler);

        char[] cbuf = {'t', 'e', 's', 't'};
        writer.write(cbuf);
    }
}
