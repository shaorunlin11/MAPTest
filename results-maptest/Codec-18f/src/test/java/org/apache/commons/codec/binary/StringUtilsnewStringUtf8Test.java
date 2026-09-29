package org.apache.commons.codec.binary;

import org.junit.Test;
import static org.junit.Assert.*;

public class StringUtilsnewStringUtf8Test {

    @Test
    public void testNewStringUtf8() throws Exception {
        byte[] input = "Hello, World!".getBytes("UTF-8");
        String result = StringUtils.newStringUtf8(input);
        assertEquals("Hello, World!", result);
    }

    @Test
    public void testNewStringUtf8WithEmptyArray() throws Exception {
        byte[] input = new byte[0];
        String result = StringUtils.newStringUtf8(input);
        assertEquals("", result);
    }

    @Test
    public void testNewStringUtf8WithNull() throws Exception {
        byte[] input = null;
        String result = StringUtils.newStringUtf8(input);
        assertNull(result);
    }
}
