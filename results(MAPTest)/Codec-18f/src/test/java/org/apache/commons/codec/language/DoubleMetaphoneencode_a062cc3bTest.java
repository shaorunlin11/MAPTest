package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.junit.Test;
import static org.junit.Assert.*;

public class DoubleMetaphoneencode_a062cc3bTest {

    @Test
    public void testEncodeWithNonNullString() throws EncoderException {
        DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
        String input = "test";
        Object result = doubleMetaphone.encode(input);
        assertTrue(result instanceof String);
    }

    @Test(expected = EncoderException.class)
    public void testEncodeWithNonStringInput() throws EncoderException {
        DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
        Object input = 123;
        doubleMetaphone.encode(input);
    }

    @Test
    public void testEncodeWithNullInput() throws EncoderException {
        DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
        Object result = doubleMetaphone.encode(null);
        assertNull(result);
    }
}
