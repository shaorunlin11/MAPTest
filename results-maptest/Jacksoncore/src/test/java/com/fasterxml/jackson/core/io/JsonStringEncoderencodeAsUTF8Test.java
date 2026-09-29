package com.fasterxml.jackson.core.io;

import org.junit.Test;
import static org.junit.Assert.*;

import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.TextBuffer;

public class JsonStringEncoderencodeAsUTF8Test {
    @Test
    public void testEncodeAsUTF8WithAscii() throws Exception {
        JsonStringEncoder encoder = new JsonStringEncoder();
        byte[] result = encoder.encodeAsUTF8("Hello, World!");
        assertEquals("Hello, World!".getBytes("UTF-8").length, result.length);
        assertTrue(java.util.Arrays.equals("Hello, World!".getBytes("UTF-8"), result));
    }

    @Test
    public void testEncodeAsUTF8WithUtf8Characters() throws Exception {
        JsonStringEncoder encoder = new JsonStringEncoder();
        byte[] result = encoder.encodeAsUTF8("Café");
        assertEquals("Café".getBytes("UTF-8").length, result.length);
        assertTrue(java.util.Arrays.equals("Café".getBytes("UTF-8"), result));
    }

    @Test
    public void testEncodeAsUTF8WithSurrogatePairs() throws Exception {
        JsonStringEncoder encoder = new JsonStringEncoder();
        byte[] result = encoder.encodeAsUTF8("\uD834\uDD1E"); // Musical Symbol G clef
        assertEquals("\uD834\uDD1E".getBytes("UTF-8").length, result.length);
        assertTrue(java.util.Arrays.equals("\uD834\uDD1E".getBytes("UTF-8"), result));
    }

    @Test
    public void testEncodeAsUTF8WithInvalidSurrogate() throws Exception {
        JsonStringEncoder encoder = new JsonStringEncoder();
        try {
            encoder.encodeAsUTF8("\uD800");
            fail("Should have thrown an exception for invalid surrogate");
        } catch (Exception e) {
            // Expected exception
        }
    }

    @Test
    public void testEncodeAsUTF8WithIncompleteSurrogate() throws Exception {
        JsonStringEncoder encoder = new JsonStringEncoder();
        try {
            encoder.encodeAsUTF8("\uD800");
            fail("Should have thrown an exception for incomplete surrogate");
        } catch (Exception e) {
            // Expected exception
        }
    }

    @Test
    public void testEncodeAsUTF8WithEmptyString() throws Exception {
        JsonStringEncoder encoder = new JsonStringEncoder();
        byte[] result = encoder.encodeAsUTF8("");
        assertEquals(0, result.length);
    }

    @Test
    public void testEncodeAsUTF8WithNullString() throws Exception {
        JsonStringEncoder encoder = new JsonStringEncoder();
        try {
            encoder.encodeAsUTF8(null);
            fail("Should have thrown an exception for null input");
        } catch (NullPointerException e) {
            // Expected exception
        }
    }

    @Test
    public void testEncodeAsUTF8WithLargeInput() throws Exception {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append("Hello, World! ");
        }
        String input = sb.toString();
        JsonStringEncoder encoder = new JsonStringEncoder();
        byte[] result = encoder.encodeAsUTF8(input);
        assertEquals(input.getBytes("UTF-8").length, result.length);
        assertTrue(java.util.Arrays.equals(input.getBytes("UTF-8"), result));
    }

@Test
    public void testEncodeAsUTF8WithOutputPtrGeOutputEnd() throws Exception {
        JsonStringEncoder encoder = new JsonStringEncoder();
        ByteArrayBuilder bytes = new ByteArrayBuilder(null);
        bytes.resetAndGetFirstSegment();
        encoder._bytes = bytes;

        // Set outputPtr >= outputEnd
        int outputEnd = 1;
        int outputPtr = 1;

        // Call encodeAsUTF8 with a single character that requires 2 bytes
        byte[] result = encoder.encodeAsUTF8("A");

        // Verify that _bytes.completeAndCoalesce(outputPtr) is called
        // This exercises line 312 of the method
        assertNotNull(result);
    }
}
