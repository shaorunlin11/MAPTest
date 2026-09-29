package org.apache.commons.codec.net;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BCodecgetEncodingTest {

    @Test
    public void testGetEncodingReturnsB() throws Exception {
        BCodec bCodec = new BCodec();
        String result = bCodec.getEncoding();
        assertEquals("B", result);
    }
}
