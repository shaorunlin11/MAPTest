package com.fasterxml.jackson.core.json;

import java.io.InputStream;
import java.io.ByteArrayInputStream;
import org.junit.Test;
import static org.junit.Assert.*;

public class UTF8StreamJsonParsergetInputSourceTest {
    @Test
    public void testGetInputSource() throws Exception {
        // Create a mock InputStream
        InputStream mockInputStream = new ByteArrayInputStream(new byte[0]);

        // Create an instance of UTF8StreamJsonParser with the mock InputStream
        com.fasterxml.jackson.core.util.BufferRecycler bufferRecycler = new com.fasterxml.jackson.core.util.BufferRecycler();
        com.fasterxml.jackson.core.io.IOContext ioContext = new com.fasterxml.jackson.core.io.IOContext(bufferRecycler, null, false);
        com.fasterxml.jackson.core.ObjectCodec objectCodec = null;
        com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer byteQuadsCanonicalizer = null;
        byte[] inputBuffer = new byte[0];
        boolean bufferRecyclable = false;

        com.fasterxml.jackson.core.json.UTF8StreamJsonParser parser = new com.fasterxml.jackson.core.json.UTF8StreamJsonParser(
            ioContext, 
            0, 
            mockInputStream, 
            objectCodec, 
            byteQuadsCanonicalizer, 
            inputBuffer, 
            0, 
            0, 
            bufferRecyclable
        );

        // Call the method under test
        Object result = parser.getInputSource();

        // Verify the result
        assertTrue("Expected InputStream instance", result instanceof InputStream);
        assertEquals("Expected the same InputStream instance", mockInputStream, result);
    }
}
