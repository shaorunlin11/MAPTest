package org.apache.commons.codec.binary;

import org.junit.Test;
import static org.junit.Assert.*;

public class Base64isUrlSafeTest {
    @Test
    public void testIsUrlSafe_DefaultConstructor() throws Exception {
        Base64 base64 = new Base64();
        assertFalse(base64.isUrlSafe());
    }

    @Test
    public void testIsUrlSafe_UrlSafeConstructor() throws Exception {
        Base64 base64 = new Base64(true);
        assertTrue(base64.isUrlSafe());
    }

    @Test
    public void testIsUrlSafe_CustomLineLength() throws Exception {
        Base64 base64 = new Base64(10);
        assertFalse(base64.isUrlSafe());
    }

    @Test
    public void testIsUrlSafe_CustomLineSeparator() throws Exception {
        Base64 base64 = new Base64(10, new byte[]{'\r', '\n'});
        assertFalse(base64.isUrlSafe());
    }

    @Test
    public void testIsUrlSafe_CustomConstructor() throws Exception {
        Base64 base64 = new Base64(10, new byte[]{'\r', '\n'}, true);
        assertTrue(base64.isUrlSafe());
    }
}
