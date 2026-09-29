package com.fasterxml.jackson.core.io;

import org.junit.Test;
import org.junit.Assert;

import java.io.InputStream;
import java.io.IOException;

public class MergedStreammarkSupportedTest {

    @Test
    public void testMarkSupported() throws Exception {
        // Create a mock IOContext (no real implementation needed)
        IOContext ctxt = new IOContext(null, null, false);

        // Create a mock InputStream that supports mark
        InputStream in = new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }

            @Override
            public boolean markSupported() {
                return true;
            }
        };

        // Create a MergedStream with _b not null
        byte[] buf = new byte[10];
        MergedStream stream1 = new MergedStream(ctxt, in, buf, 0, 10);
        Assert.assertFalse("markSupported should return false when _b is not null", stream1.markSupported());

        // Create a MergedStream with _b null
        MergedStream stream2 = new MergedStream(ctxt, in, null, 0, 0);
        Assert.assertTrue("markSupported should return true when _b is null and _in supports mark", stream2.markSupported());
    }
}
