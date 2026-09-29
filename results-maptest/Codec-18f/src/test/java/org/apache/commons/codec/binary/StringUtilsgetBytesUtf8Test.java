package org.apache.commons.codec.binary;

import org.junit.Test;
import static org.junit.Assert.*;

public class StringUtilsgetBytesUtf8Test {

    @Test
    public void testGetBytesUtf8() {
        String input = "Hello, World!";
        byte[] result = StringUtils.getBytesUtf8(input);
        assertNotNull("Result should not be null", result);
        assertEquals("Length should match", input.getBytes(java.nio.charset.Charset.forName("UTF-8")).length, result.length);
    }

    @Test
    public void testGetBytesUtf8WithNullInput() {
        byte[] result = StringUtils.getBytesUtf8(null);
        assertNull("Result should be null", result);
    }
}
