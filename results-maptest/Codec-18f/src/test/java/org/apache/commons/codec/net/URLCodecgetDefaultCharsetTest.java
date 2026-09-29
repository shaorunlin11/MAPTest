package org.apache.commons.codec.net;

import org.junit.Test;
import static org.junit.Assert.*;

public class URLCodecgetDefaultCharsetTest {
    @Test
    public void testGetDefaultCharset() throws Exception {
        // Create a URLCodec instance with a specific charset
        URLCodec codec = new URLCodec("UTF-8");
        assertEquals("UTF-8", codec.getDefaultCharset());

        // Create a URLCodec instance with a different charset
        URLCodec codec2 = new URLCodec("ISO-8859-1");
        assertEquals("ISO-8859-1", codec2.getDefaultCharset());

        // Verify that the default constructor uses UTF-8
        URLCodec codec3 = new URLCodec();
        assertEquals("UTF-8", codec3.getDefaultCharset());
    }
}
