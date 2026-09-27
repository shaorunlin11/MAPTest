package org.apache.commons.codec.language.bm;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.junit.Test;
import static org.junit.Assert.*;

public class BeiderMorseEncoderencode_29d93ec6Test {

    @Test
    public void testEncodeWithNonNullString() throws EncoderException {
        BeiderMorseEncoder encoder = new BeiderMorseEncoder();
        String input = "test";
        Object result = encoder.encode(input);
        assertNotNull("Result should not be null", result);
        assertTrue("Result should be a String", result instanceof String);
    }

    @Test(expected = EncoderException.class)
    public void testEncodeWithNonStringInput() throws EncoderException {
        BeiderMorseEncoder encoder = new BeiderMorseEncoder();
        Integer input = 123;
        encoder.encode(input);
    }
}
