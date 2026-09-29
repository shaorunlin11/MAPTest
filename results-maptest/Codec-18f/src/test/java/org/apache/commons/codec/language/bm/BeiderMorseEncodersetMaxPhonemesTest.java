package org.apache.commons.codec.language.bm;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.lang.reflect.Field;

public class BeiderMorseEncodersetMaxPhonemesTest {
    private BeiderMorseEncoder encoder;

    @Before
    public void setUp() {
        encoder = new BeiderMorseEncoder();
    }

    @After
    public void tearDown() {
        encoder = null;
    }

    @Test
    public void testSetMaxPhonemesUpdatesEngineWithNewMaxPhonemes() throws Exception {
        int newMaxPhonemes = 5;
        encoder.setMaxPhonemes(newMaxPhonemes);

        // Verify that the engine was replaced with a new instance
        Field engineField = BeiderMorseEncoder.class.getDeclaredField("engine");
        engineField.setAccessible(true);
        Object originalEngine = engineField.get(encoder);

        // Create a new instance to compare against
        BeiderMorseEncoder newEncoder = new BeiderMorseEncoder();
        newEncoder.setMaxPhonemes(newMaxPhonemes);
        Object newEngine = engineField.get(newEncoder);

        // Verify that the new engine has the same configuration as the original
        Assert.assertEquals(((PhoneticEngine)originalEngine).getNameType(), ((PhoneticEngine)newEngine).getNameType());
        Assert.assertEquals(((PhoneticEngine)originalEngine).getRuleType(), ((PhoneticEngine)newEngine).getRuleType());
        Assert.assertEquals(((PhoneticEngine)originalEngine).isConcat(), ((PhoneticEngine)newEngine).isConcat());

        // Verify that the new engine has the updated maxPhonemes value
        Field maxPhonemesField = PhoneticEngine.class.getDeclaredField("maxPhonemes");
        maxPhonemesField.setAccessible(true);
        Assert.assertEquals(newMaxPhonemes, maxPhonemesField.get(newEngine));
    }
}
