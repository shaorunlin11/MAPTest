package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonpCharacterEscapesgetEscapeSequenceTest {
    @Test
    public void testGetEscapeSequenceFor2028() {
        JsonpCharacterEscapes instance = new JsonpCharacterEscapes();
        SerializableString result = instance.getEscapeSequence(0x2028);
        assertNotNull("Should not return null for 0x2028", result);
        assertEquals("Should return escapeFor2028 for 0x2028", "\\u2028", result.getValue());
    }

    @Test
    public void testGetEscapeSequenceFor2029() {
        JsonpCharacterEscapes instance = new JsonpCharacterEscapes();
        SerializableString result = instance.getEscapeSequence(0x2029);
        assertNotNull("Should not return null for 0x2029", result);
        assertEquals("Should return escapeFor2029 for 0x2029", "\\u2029", result.getValue());
    }

    @Test
    public void testGetEscapeSequenceForOtherValues() {
        JsonpCharacterEscapes instance = new JsonpCharacterEscapes();
        SerializableString result = instance.getEscapeSequence('a');
        assertNull("Should return null for values other than 0x2028 and 0x2029", result);
    }
}
