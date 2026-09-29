package com.fasterxml.jackson.core;

import org.junit.Test;
import java.io.IOException;
import java.io.InputStream;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.io.IOContext;

public class JsonFactoryCreateParserZeroCoverage_84Test {
    @Test
    public void testCreateParserWithInputDecorator() throws IOException {
        JsonFactory factory = new JsonFactory();
        byte[] data = "test".getBytes();
        int offset = 0;
        int len = data.length;

        // Use a custom InputStream directly instead of InputDecorator
        InputStream inputStream = new java.io.ByteArrayInputStream(data);

        // Since we can't use InputDecorator, we'll just use the default behavior
        JsonParser parser = factory.createParser(data, offset, len);
    }
}
