package org.apache.commons.codec.language;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class Soundexencode_a062cc3bTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testEncode_InvalidInputType_ThrowsEncoderException() throws Exception {
        Soundex soundex = new Soundex();
        Object invalidInput = new Object();

        thrown.expect(org.apache.commons.codec.EncoderException.class);
        thrown.expectMessage("Parameter supplied to Soundex encode is not of type java.lang.String");

        soundex.encode(invalidInput);
    }

    @Test
    public void testEncode_ValidStringInput_DelegatesToSoundexMethod() throws Exception {
        Soundex soundex = new Soundex();
        String input = "test";

        // Since we cannot directly verify the delegation without reflection,
        // we can only confirm that no exception is thrown for valid input
        Assert.assertNotNull(soundex.encode(input));
    }
}
