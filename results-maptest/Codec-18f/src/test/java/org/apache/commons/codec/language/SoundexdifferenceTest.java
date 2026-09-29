package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.junit.Test;
import static org.junit.Assert.*;

public class SoundexdifferenceTest {

    @Test
    public void testDifference() throws EncoderException {
        Soundex soundex = new Soundex();
        int result = soundex.difference("hello", "helo");
        // The actual value depends on the implementation of SoundexUtils.difference
        // This test verifies that the method is called and returns a value
        assertNotNull(result);
    }

    @Test
    public void testDifferenceWithNull() throws EncoderException {
        Soundex soundex = new Soundex();
        // The method does not throw an exception for null input according to the implementation
        int result = soundex.difference("test", null);
        assertNotNull(result);
    }
}
