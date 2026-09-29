package com.fasterxml.jackson.core.json;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonFactory.Feature;

import com.fasterxml.jackson.core.io.NumberOutput;


public class UTF8JsonGeneratorWriteNumberZeroCoverageTest {
    @Test
    public void testWriteNumberShort() throws IOException {
        // Create a ByteArrayOutputStream to act as the output stream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Create a JsonFactory to properly initialize the generator
        JsonFactory jsonFactory = new JsonFactory();

        // Create an instance of UTF8JsonGenerator with proper initialization
        JsonGenerator generator = jsonFactory.createGenerator(outputStream);

        // Call the writeNumber method with a short value
        generator.writeNumber((short) 0);

        // Ensure that the method execution reaches the target line 882
        // by verifying that the _verifyValueWrite method is called
        // and that the NumberOutput.outputInt method is executed.
        // This is achieved by ensuring that the method completes without error.
    }
}
