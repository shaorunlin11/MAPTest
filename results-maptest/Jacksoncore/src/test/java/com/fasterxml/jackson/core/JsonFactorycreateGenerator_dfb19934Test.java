package com.fasterxml.jackson.core;

import org.junit.Test;
import org.junit.Assert;
import java.io.Writer;
import java.io.StringWriter;
import java.io.IOException;

public class JsonFactorycreateGenerator_dfb19934Test {
    @Test
    public void testCreateGenerator() throws Exception {
        JsonFactory factory = new JsonFactory();
        Writer writer = new StringWriter();

        JsonGenerator generator = factory.createGenerator(writer);

        Assert.assertNotNull(generator);
    }

    @Test(expected = IOException.class)
    public void testCreateGeneratorWithIOException() throws Exception {
        JsonFactory factory = new JsonFactory();
        Writer writer = new Writer() {
            @Override
            public void write(char[] cbuf, int off, int len) throws IOException {
                throw new IOException("Simulated I/O error");
            }

            @Override
            public void flush() throws IOException {
                throw new IOException("Simulated I/O error");
            }

            @Override
            public void close() throws IOException {
                throw new IOException("Simulated I/O error");
            }
        };

        // Ensure that the Writer's methods are called during generator creation
        factory.createGenerator(writer);
        writer.write(new char[0], 0, 0);
    }
}
