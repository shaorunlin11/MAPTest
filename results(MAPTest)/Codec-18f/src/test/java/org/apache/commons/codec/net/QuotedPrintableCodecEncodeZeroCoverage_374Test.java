package org.apache.commons.codec.net;

import org.junit.Test;

public class QuotedPrintableCodecEncodeZeroCoverage_374Test {
    @Test
    public void testEncodeWithNullString() throws Exception {
        QuotedPrintableCodec codec = new QuotedPrintableCodec();
        String result = codec.encode(null, "US-ASCII");
        // Target line 596 is the null check in the encode method
        // This test ensures that the null check is executed
        // No assertion needed as the goal is to execute the code path
    }

@Test
    public void testEncodeWithNonNullStringAndCharset() throws Exception {
        QuotedPrintableCodec codec = new QuotedPrintableCodec();
        String result = codec.encode("test string", "US-ASCII");
        // Target line 599 is the return statement in the encode method
        // This test ensures that the code path through the return statement is executed
    }
}
