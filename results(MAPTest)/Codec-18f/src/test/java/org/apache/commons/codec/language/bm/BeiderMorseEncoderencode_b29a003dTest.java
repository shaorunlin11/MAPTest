package org.apache.commons.codec.language.bm;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.junit.Test;
import static org.junit.Assert.*;

public class BeiderMorseEncoderencode_b29a003dTest {

    @Test
    public void testEncode_NullInput_ReturnsNull() throws EncoderException {
        BeiderMorseEncoder encoder = new BeiderMorseEncoder();
        String result = encoder.encode(null);
        assertNull(result);
    }

    @Test
    public void testEncode_ValidInput_DelegatesToEngine() throws EncoderException {
        BeiderMorseEncoder encoder = new BeiderMorseEncoder();
        String input = "test";
        String result = encoder.encode(input);
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
}
