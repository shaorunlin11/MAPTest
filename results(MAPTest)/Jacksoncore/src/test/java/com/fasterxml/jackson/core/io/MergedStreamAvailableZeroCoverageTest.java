package com.fasterxml.jackson.core.io;

import org.junit.Test;
import java.io.InputStream;

public class MergedStreamAvailableZeroCoverageTest {
    @Test
    public void testAvailableWithBNull() throws Exception {
        // Create a mock IOContext (no actual implementation needed for this test)
        IOContext ctxt = new IOContext(null, null, false);

        // Create a mock InputStream (no actual implementation needed for this test)
        InputStream in = new InputStream() {
            @Override
            public int read() {
                return 0;
            }
        };

        // Create a MergedStream with _b set to null
        MergedStream mergedStream = new MergedStream(ctxt, in, null, 0, 0);

        // Call the available method
        mergedStream.available();
    }
}
