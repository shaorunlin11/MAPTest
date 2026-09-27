package org.apache.commons.codec.binary;

import org.junit.Test;
import static org.junit.Assert.*;

import java.nio.charset.Charset;
import org.apache.commons.codec.Charsets;

public class StringUtilsgetBytesUsAsciiTest {

    @Test
    public void testGetBytesUsAscii() throws Exception {
        String input = "Hello, World!";
        byte[] result = StringUtils.getBytesUsAscii(input);
        assertNotNull("Result should not be null", result);
        assertEquals("Length should match", input.getBytes(Charsets.US_ASCII).length, result.length);
        for (int i = 0; i < result.length; i++) {
            assertEquals("Byte at index " + i + " should match", input.getBytes(Charsets.US_ASCII)[i], result[i]);
        }
    }

    @Test
    public void testGetBytesUsAsciiWithNull() {
        byte[] result = StringUtils.getBytesUsAscii(null);
        assertNull("Result should be null", result);
    }
}
