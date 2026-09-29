package org.apache.commons.codec.language;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class SoundexsetMaxLengthTest {
    private Soundex soundex;

    @Before
    public void setUp() {
        soundex = new Soundex();
    }

    @After
    public void tearDown() {
        soundex = null;
    }

    @Test
    public void testSetMaxLength() throws Exception {
        int expectedMaxLength = 6;
        soundex.setMaxLength(expectedMaxLength);

        // Use reflection to verify the field value
        java.lang.reflect.Field maxLengthField = Soundex.class.getDeclaredField("maxLength");
        maxLengthField.setAccessible(true);
        int actualMaxLength = (int) maxLengthField.get(soundex);

        Assert.assertEquals("The maxLength should be set correctly", expectedMaxLength, actualMaxLength);
    }
}
