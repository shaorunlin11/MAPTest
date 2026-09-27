package org.apache.commons.codec.binary;

import org.junit.Test;
import static org.junit.Assert.*;

public class StringUtilsnewStringUtf16BeTest {

    @Test
    public void testNewStringUtf16Be() throws Exception {
        byte[] bytes = "Hello, World!".getBytes("UTF-16BE");
        String result = StringUtils.newStringUtf16Be(bytes);
        assertEquals("Hello, World!", result);
    }

    @Test
    public void testNewStringUtf16BeWithEmptyArray() {
        byte[] bytes = new byte[0];
        String result = StringUtils.newStringUtf16Be(bytes);
        assertEquals("", result);
    }

    @Test
    public void testNewStringUtf16BeWithNull() {
        byte[] bytes = null;
        String result = StringUtils.newStringUtf16Be(bytes);
        assertNull(result);
    }
}
