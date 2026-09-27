package org.apache.commons.codec.binary;

import org.junit.Test;
import static org.junit.Assert.*;

public class StringUtilsnewStringIso8859_1Test {

    @Test
    public void testNewStringIso8859_1() throws Exception {
        byte[] bytes = {72, 101, 108, 108, 111};
        String result = StringUtils.newStringIso8859_1(bytes);
        assertEquals("Hello", result);
    }

    @Test
    public void testNewStringIso8859_1_NullBytes() {
        byte[] bytes = null;
        String result = StringUtils.newStringIso8859_1(bytes);
        assertNull(result);
    }

    @Test
    public void testNewStringIso8859_1_EmptyBytes() {
        byte[] bytes = {};
        String result = StringUtils.newStringIso8859_1(bytes);
        assertEquals("", result);
    }
}
