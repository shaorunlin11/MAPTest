package org.apache.commons.codec.binary;

import org.junit.Test;
import static org.junit.Assert.*;

public class StringUtilsgetBytesUtf16LeTest {

    @Test
    public void testGetBytesUtf16Le() throws Exception {
        String input = "Hello, World!";
        byte[] result = StringUtils.getBytesUtf16Le(input);
        assertNotNull("Result should not be null", result);
        assertTrue("Result should not be empty", result.length > 0);

        // Verify that the result is UTF-16LE encoded
        String decoded = new String(result, java.nio.charset.Charset.forName("UTF-16LE"));
        assertEquals("Decoded string should match original", input, decoded);
    }
}
