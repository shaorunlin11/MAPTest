package org.apache.commons.codec.net;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class QuotedPrintableCodecgetDefaultCharsetTest {

    @Test
    public void testGetDefaultCharset() {
        QuotedPrintableCodec codec = new QuotedPrintableCodec();
        String expectedCharset = "UTF-8";
        assertEquals(expectedCharset, codec.getDefaultCharset());
    }
}
