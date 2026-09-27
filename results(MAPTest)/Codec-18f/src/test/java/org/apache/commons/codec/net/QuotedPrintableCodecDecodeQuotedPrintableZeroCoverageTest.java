package org.apache.commons.codec.net;

import org.junit.Test;

public class QuotedPrintableCodecDecodeQuotedPrintableZeroCoverageTest {
    @Test
    public void testDecodeQuotedPrintableWithNullBytes() throws Exception {
        // This test is designed to execute target lines 352 by passing null bytes
        // which triggers the null check at the beginning of the decodeQuotedPrintable method.
        QuotedPrintableCodec.decodeQuotedPrintable(null);
    }

@Test
    public void testDecodeQuotedPrintableWithEscapedCharacters() throws Exception {
        // This test is designed to execute target lines 356 by passing bytes that trigger the
        // escaped character handling logic in the decodeQuotedPrintable method.
        final byte[] bytes = new byte[] { (byte) 0x3D, (byte) 0x31, (byte) 0x32, (byte) 0x33 };
        QuotedPrintableCodec.decodeQuotedPrintable(bytes);
    }
}
