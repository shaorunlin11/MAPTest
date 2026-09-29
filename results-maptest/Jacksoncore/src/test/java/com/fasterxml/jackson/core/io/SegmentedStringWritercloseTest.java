package com.fasterxml.jackson.core.io;

import java.io.IOException;
import org.junit.Test;
import org.junit.Assert;
import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.TextBuffer;

public class SegmentedStringWritercloseTest {

    @Test
    public void testClose() throws IOException {
        BufferRecycler bufferRecycler = new BufferRecycler();
        SegmentedStringWriter writer = new SegmentedStringWriter(bufferRecycler);
        writer.close();
        // The close method is a no-op, so no assertions are needed
    }
}
