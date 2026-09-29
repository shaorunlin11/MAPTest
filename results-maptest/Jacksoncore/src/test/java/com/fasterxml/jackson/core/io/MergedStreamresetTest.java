package com.fasterxml.jackson.core.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import org.junit.Test;
import org.junit.Assert;

public class MergedStreamresetTest {
    @Test
    public void testResetWhenBIsNull() throws Exception {
        // Create a mock InputStream that supports mark/reset
        InputStream mockIn = new BufferedInputStream(new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        });
        // Ensure the stream is marked before reset
        mockIn.mark(1);

        // Create a MergedStream with _b set to null
        MergedStream stream = new MergedStream(null, mockIn, null, 0, 0);

        // Call reset()
        stream.reset();

        // Since _b is null, the reset() should call _in.reset()
        // We can verify this by checking if the underlying stream's reset was called
        // However, since we don't have a way to verify that directly,
        // we can only confirm that no exception is thrown
        // and that the method completes without error
    }

    @Test
    public void testResetWhenBIsNotNull() throws Exception {
        // Create a mock InputStream
        InputStream mockIn = new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        };

        // Create a MergedStream with _b not null
        byte[] buffer = new byte[10];
        MergedStream stream = new MergedStream(null, mockIn, buffer, 0, 10);

        // Call reset()
        stream.reset();

        // Since _b is not null, the method should do nothing
        // No exception should be thrown
    }

    @Test(expected = IOException.class)
    public void testResetThrowsIOException() throws Exception {
        // Create a mock InputStream that throws IOException on reset
        InputStream mockIn = new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }

            @Override
            public void reset() throws IOException {
                throw new IOException("Simulated exception");
            }
        };

        // Create a MergedStream with _b set to null
        MergedStream stream = new MergedStream(null, mockIn, null, 0, 0);

        // Call reset(), which should throw IOException
        stream.reset();
    }
}
