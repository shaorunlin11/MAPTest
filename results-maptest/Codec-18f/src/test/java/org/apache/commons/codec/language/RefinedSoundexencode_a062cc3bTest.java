package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.junit.Test;
import static org.junit.Assert.*;

public class RefinedSoundexencode_a062cc3bTest {

    @Test
    public void testEncodeWithNonNullString() throws EncoderException {
        RefinedSoundex refinedSoundex = new RefinedSoundex();
        String input = "example";
        Object result = refinedSoundex.encode(input);
        assertNotNull(result);
        assertTrue(result instanceof String);
    }

    @Test(expected = EncoderException.class)
    public void testEncodeWithNonStringInput() throws EncoderException {
        RefinedSoundex refinedSoundex = new RefinedSoundex();
        Object input = 123;
        refinedSoundex.encode(input);
    }

    @Test(expected = EncoderException.class)
    public void testEncodeWithNullInput() throws EncoderException {
        RefinedSoundex refinedSoundex = new RefinedSoundex();
        Object input = null;
        refinedSoundex.encode(input);
    }
}
