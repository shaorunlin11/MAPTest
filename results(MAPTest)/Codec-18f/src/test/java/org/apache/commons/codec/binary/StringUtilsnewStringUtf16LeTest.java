package org.apache.commons.codec.binary;

import org.junit.Test;
import static org.junit.Assert.*;

public class StringUtilsnewStringUtf16LeTest {

    @Test
    public void testNewStringUtf16Le() throws Exception {
        byte[] bytes = {(byte) 0x48, (byte) 0x00, (byte) 0x65, (byte) 0x00, (byte) 0x6C, (byte) 0x00, (byte) 0x6C, (byte) 0x00, (byte) 0x6F, (byte) 0x00};
        String result = StringUtils.newStringUtf16Le(bytes);
        assertEquals("Hello", result);
    }

    @Test
    public void testNewStringUtf16LeWithEmptyArray() {
        byte[] bytes = new byte[0];
        String result = StringUtils.newStringUtf16Le(bytes);
        assertEquals("", result);
    }

    @Test
    public void testNewStringUtf16LeWithNull() {
        byte[] bytes = null;
        String result = StringUtils.newStringUtf16Le(bytes);
        assertNull(result);
    }
}
