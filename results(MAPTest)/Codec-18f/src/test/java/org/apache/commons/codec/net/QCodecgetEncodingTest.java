package org.apache.commons.codec.net;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class QCodecgetEncodingTest {

    @Test
    public void testGetEncodingReturnsQ() throws Exception {
        QCodec qCodec = new QCodec();
        String encoding = qCodec.getEncoding();
        assertEquals("Q", encoding);
    }
}
