package com.fasterxml.jackson.core.io;

import org.junit.Test;
import static org.junit.Assert.*;

public class SerializedStringhashCodeTest {
    @Test
    public void testHashCode() throws Exception {
        String testValue = "test";
        SerializedString serializedString = new SerializedString(testValue);
        int expectedHashCode = testValue.hashCode();
        int actualHashCode = serializedString.hashCode();
        assertEquals("Hash code should match the underlying string's hash code", expectedHashCode, actualHashCode);
    }

    @Test
    public void testHashCodeWithNullValue() throws Exception {
        try {
            SerializedString serializedString = new SerializedString(null);
            serializedString.hashCode();
            fail("Expected IllegalStateException was not thrown");
        } catch (IllegalStateException e) {
            // Expected exception
        }
    }
}
