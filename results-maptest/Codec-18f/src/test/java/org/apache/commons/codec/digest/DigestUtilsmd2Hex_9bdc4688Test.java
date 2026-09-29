package org.apache.commons.codec.digest;

import org.junit.Test;
import static org.junit.Assert.*;

public class DigestUtilsmd2Hex_9bdc4688Test {
    @Test
    public void testMd2Hex() {
        String input = "Hello, World!";
        String expected = "1c8f1e6a94aaa7145210bf90bb52871a";
        String result = DigestUtils.md2Hex(input);
        assertEquals(expected, result);
    }

    @Test
    public void testMd2HexEmptyString() {
        String input = "";
        String expected = "8350e5a3e24c153df2275c9f80692773";
        String result = DigestUtils.md2Hex(input);
        assertEquals(expected, result);
    }

    @Test
    public void testMd2HexNullInput() {
        String input = null;
        try {
            DigestUtils.md2Hex(input);
            fail("Expected NullPointerException to be thrown");
        } catch (NullPointerException e) {
            // Expected exception
        }
    }
}
