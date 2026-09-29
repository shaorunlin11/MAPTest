package org.apache.commons.codec.net;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BCodecgetDefaultCharsetTest {

    @Test
    public void testGetDefaultCharset() throws Exception {
        BCodec codec = new BCodec();
        assertEquals("UTF-8", codec.getDefaultCharset());
    }

    @Test
    public void testGetDefaultCharsetWithCustomCharset() throws Exception {
        BCodec codec = new BCodec("UTF-16");
        assertEquals("UTF-16", codec.getDefaultCharset());
    }
}
