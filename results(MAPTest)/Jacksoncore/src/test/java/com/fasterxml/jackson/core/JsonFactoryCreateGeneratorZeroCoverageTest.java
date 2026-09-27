package com.fasterxml.jackson.core;

import org.junit.Test;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonEncoding;

public class JsonFactoryCreateGeneratorZeroCoverageTest {
    @Test
    public void testCreateGeneratorWithNonNullOutputStreamAndEncoding() throws IOException {
        JsonFactory factory = new JsonFactory();
        OutputStream out = new ByteArrayOutputStream();
        JsonEncoding enc = JsonEncoding.UTF8;

        // This call should execute the target lines 1126
        JsonGenerator generator = factory.createGenerator(out, enc);

        // Ensure that the generator is not null
        assert generator != null;
    }

@Test
    public void testCreateGeneratorWithNonNullOutputStreamAndNonUTF8Encoding() throws IOException {
        JsonFactory factory = new JsonFactory();
        OutputStream out = new ByteArrayOutputStream();
        JsonEncoding enc = JsonEncoding.UTF16_BE;

        // This call should execute the target lines 1131
        JsonGenerator generator = factory.createGenerator(out, enc);

        // Ensure that the generator is not null
        assert generator != null;
    }
}
