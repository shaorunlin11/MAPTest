package com.fasterxml.jackson.core.io;

import org.junit.Test;
import java.io.InputStream;

public class MergedStreamReadZeroCoverageTest {
    @Test
    public void testReadWithNonEmptyBuffer() throws Exception {
        IOContext ctxt = new IOContext(null, null, false);
        InputStream in = new InputStream() {
            @Override
            public int read() {
                return -1;
            }
        };
        byte[] buf = {1, 2, 3};
        int start = 0;
        int end = 3;

        MergedStream mergedStream = new MergedStream(ctxt, in, buf, start, end);

        // Call read() to execute target line 56
        mergedStream.read();
    }

@Test
    public void testReadWithNullBuffer() throws Exception {
        IOContext ctxt = new IOContext(null, null, false);
        InputStream in = new InputStream() {
            @Override
            public int read() {
                return -1;
            }
        };
        byte[] buf = null;
        int start = 0;
        int end = 0;

        MergedStream mergedStream = new MergedStream(ctxt, in, buf, start, end);

        // Call read() to execute target line 63
        mergedStream.read();
    }
}
