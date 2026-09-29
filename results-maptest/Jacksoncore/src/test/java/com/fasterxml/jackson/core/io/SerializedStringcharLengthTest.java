package com.fasterxml.jackson.core.io;

import org.junit.Test;
import static org.junit.Assert.*;

public class SerializedStringcharLengthTest {
    @Test
    public void testCharLength() throws Exception {
        String testValue = "Hello, world!";
        SerializedString serializedString = new SerializedString(testValue);
        assertEquals("charLength should return the correct length of the internal string", testValue.length(), serializedString.charLength());
    }

    @Test
    public void testCharLengthWithEmptyString() throws Exception {
        String testValue = "";
        SerializedString serializedString = new SerializedString(testValue);
        assertEquals("charLength should return 0 for an empty string", 0, serializedString.charLength());
    }

    @Test
    public void testCharLengthWithNullString() throws Exception {
        try {
            new SerializedString(null);
            fail("Constructor should throw IllegalStateException for null string");
        } catch (IllegalStateException e) {
            // Expected exception
        }
    }
}
