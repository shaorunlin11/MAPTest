package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.junit.Test;
import org.junit.Assert;

public class Metaphoneencode_a062cc3bTest {

    @Test
    public void testEncodeWithNonStringInput() {
        Metaphone metaphone = new Metaphone();
        try {
            metaphone.encode(123);
            Assert.fail("Expected EncoderException to be thrown");
        } catch (EncoderException e) {
            // Expected exception
        }
    }

    @Test
    public void testEncodeWithStringInput() throws EncoderException {
        Metaphone metaphone = new Metaphone();
        String result = (String) metaphone.encode("test");
        Assert.assertNotNull(result);
    }
}
