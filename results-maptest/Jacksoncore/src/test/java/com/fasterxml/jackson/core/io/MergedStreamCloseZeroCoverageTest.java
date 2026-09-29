package com.fasterxml.jackson.core.io;

import org.junit.Test;
import java.io.InputStream;
import java.io.IOException;

public class MergedStreamCloseZeroCoverageTest {
    @Test
    public void testClose() throws IOException {
        // Create a mock InputStream
        InputStream mockIn = new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        };

        // Create a MergedStream instance
        MergedStream mergedStream = new MergedStream(null, mockIn, new byte[1], 0, 1);

        // Call close() method
        mergedStream.close();
    }
}
