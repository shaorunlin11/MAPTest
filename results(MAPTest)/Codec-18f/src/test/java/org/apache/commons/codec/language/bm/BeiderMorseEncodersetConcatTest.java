package org.apache.commons.codec.language.bm;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import java.lang.reflect.Field;


public class BeiderMorseEncodersetConcatTest {
    private BeiderMorseEncoder encoder;

    @Before
    public void setUp() {
        encoder = new BeiderMorseEncoder();
    }

    @Test
    public void testSetConcat() throws Exception {
        // Arrange
        boolean concatValue = true;

        // Act
        encoder.setConcat(concatValue);

        // Assert
        Field engineField = BeiderMorseEncoder.class.getDeclaredField("engine");
        engineField.setAccessible(true);
        PhoneticEngine newEngine = (PhoneticEngine) engineField.get(encoder);

        Assert.assertTrue(newEngine instanceof PhoneticEngine);
        Assert.assertEquals(concatValue, newEngine.isConcat());
    }
}
