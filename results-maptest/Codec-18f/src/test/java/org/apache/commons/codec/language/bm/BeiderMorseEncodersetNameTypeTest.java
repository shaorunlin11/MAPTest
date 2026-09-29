package org.apache.commons.codec.language.bm;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

import java.lang.reflect.Field;

public class BeiderMorseEncodersetNameTypeTest {
    private BeiderMorseEncoder encoder;
    private PhoneticEngine originalEngine;

    @Before
    public void setUp() throws Exception {
        encoder = new BeiderMorseEncoder();
        Field engineField = BeiderMorseEncoder.class.getDeclaredField("engine");
        engineField.setAccessible(true);
        originalEngine = (PhoneticEngine) engineField.get(encoder);
    }

    @After
    public void tearDown() throws Exception {
        encoder = null;
        originalEngine = null;
    }

    @Test
    public void testSetNameType() throws Exception {
        // Arrange
        NameType newNameType = NameType.GENERIC; // Using a valid enum value

        // Act
        encoder.setNameType(newNameType);

        // Assert
        Field engineField = BeiderMorseEncoder.class.getDeclaredField("engine");
        engineField.setAccessible(true);
        PhoneticEngine newEngine = (PhoneticEngine) engineField.get(encoder);

        // Verify that the new engine has the correct name type
        assertEquals("NameType should be set correctly", newNameType, newEngine.getNameType());

        // Verify that other properties are preserved from the original engine
        assertEquals("RuleType should be preserved", originalEngine.getRuleType(), newEngine.getRuleType());
        assertEquals("isConcat should be preserved", originalEngine.isConcat(), newEngine.isConcat());
        assertEquals("maxPhonemes should be preserved", originalEngine.getMaxPhonemes(), newEngine.getMaxPhonemes());
    }
}
