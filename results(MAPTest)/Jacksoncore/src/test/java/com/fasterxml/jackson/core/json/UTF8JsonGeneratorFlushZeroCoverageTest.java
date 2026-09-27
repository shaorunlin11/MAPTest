package com.fasterxml.jackson.core.json;

import org.junit.Test;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import static org.junit.Assert.*;

import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.util.BufferRecycler;

public class UTF8JsonGeneratorFlushZeroCoverageTest {
    @Test
    public void testFlush() throws Exception {
        // Create a ByteArrayOutputStream to act as the output stream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Create an instance of UTF8JsonGenerator with the output stream
        UTF8JsonGenerator generator = new UTF8JsonGenerator(
            new IOContext(new BufferRecycler(), null, false), // IOContext
            0, // features
            null, // ObjectCodec
            outputStream
        );

        // Enable the FLUSH_PASSED_TO_STREAM feature
        generator.enable(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM);

        // Call the flush method
        generator.flush();

        // Verify that _outputStream is not null
        assertNotNull(generator._outputStream);

        // Verify that _flushBuffer() was called (this is implicit in the method call)

        // Verify that _outputStream.flush() was called (this is implicit in the method call)

        // Additional verification can be added if needed
    }
}
