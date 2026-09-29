package org.apache.commons.codec.net;

import org.junit.Test;
import static org.junit.Assert.*;
import java.nio.charset.Charset;
import java.util.BitSet;
import org.apache.commons.codec.DecoderException;

public class QCodecdoDecodingTest {

    @Test
    public void testDoDecoding_NullInput() throws DecoderException {
        QCodec codec = new QCodec();
        assertNull(codec.doDecoding(null));
    }

    @Test
    public void testDoDecoding_NoUnderscores() throws DecoderException {
        QCodec codec = new QCodec();
        byte[] input = "test".getBytes(Charset.forName("UTF-8"));
        byte[] result = codec.doDecoding(input);
        assertNotNull(result);
        // Verify that the decoded bytes match the original input
        for (int i = 0; i < input.length; i++) {
            assertEquals(input[i], result[i]);
        }
    }

    @Test
    public void testDoDecoding_WithUnderscores() throws DecoderException {
        QCodec codec = new QCodec();
        byte[] input = "test_with_underscores".getBytes(Charset.forName("UTF-8"));
        byte[] result = codec.doDecoding(input);
        assertNotNull(result);
        // Verify that underscores were replaced with blanks
        for (int i = 0; i < input.length; i++) {
            if (input[i] == '_') {
                assertEquals((byte) 32, result[i]); // BLANK is 32
            } else {
                assertEquals(input[i], result[i]);
            }
        }
    }
}
