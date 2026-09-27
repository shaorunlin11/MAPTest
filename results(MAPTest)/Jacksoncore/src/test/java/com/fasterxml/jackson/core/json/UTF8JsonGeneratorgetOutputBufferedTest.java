package com.fasterxml.jackson.core.json;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.util.BufferRecycler;

public class UTF8JsonGeneratorgetOutputBufferedTest {
    private UTF8JsonGenerator generator;
    private ByteArrayOutputStream outputStream;

    @Before
    public void setUp() throws IOException {
        outputStream = new ByteArrayOutputStream();
        // Use the constructor that allows specifying output buffer
        byte[] outputBuffer = new byte[1024];
        // Create a valid IOContext with required parameters
        BufferRecycler bufferRecycler = new BufferRecycler();
        IOContext ioContext = new IOContext(bufferRecycler, null, false);
        generator = new UTF8JsonGenerator(ioContext, 0, null, outputStream, outputBuffer, 0, true);
    }

    @Test
    public void testGetOutputBufferedReturnsOutputTail() {
        // Initially, _outputTail is 0
        Assert.assertEquals(0, generator.getOutputBuffered());

        // Write some data to the generator
        try {
            generator.writeStartObject();
            generator.writeFieldName("test");
            generator.writeString("value");
            generator.writeEndObject();
        } catch (IOException e) {
            Assert.fail("Unexpected exception: " + e.getMessage());
        }

        // Verify that _outputTail has been updated
        Assert.assertTrue(generator.getOutputBuffered() > 0);
    }
}
