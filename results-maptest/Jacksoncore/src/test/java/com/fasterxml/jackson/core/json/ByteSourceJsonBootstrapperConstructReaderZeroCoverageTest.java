package com.fasterxml.jackson.core.json;

import org.junit.Test;
import java.io.*;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.JsonEncoding;
import org.junit.Assert;

public class ByteSourceJsonBootstrapperConstructReaderZeroCoverageTest {
    @Test
    public void testConstructReaderWithNonNullEncoding() throws Exception {
        // Create a mock IOContext that returns a non-null encoding
        IOContext context = new IOContext(null, null, false) {
            @Override
            public JsonEncoding getEncoding() {
                return JsonEncoding.UTF8;
            }
        };

        // Create a byte array input buffer
        byte[] inputBuffer = "test".getBytes("UTF-8");
        int inputStart = 0;
        int inputLen = inputBuffer.length;

        // Create a ByteSourceJsonBootstrapper instance
        ByteSourceJsonBootstrapper bootstrapper = new ByteSourceJsonBootstrapper(context, inputBuffer, inputStart, inputLen);

        // Call the method under test
        Reader reader = bootstrapper.constructReader();

        // Ensure the reader is not null
        Assert.assertNotNull(reader);

        // Close the reader to avoid resource leaks
        reader.close();
    }
}
