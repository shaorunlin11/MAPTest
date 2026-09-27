package com.fasterxml.jackson.core.io;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.Serializable;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.util.BufferRecyclers;

public class SerializedStringasUnquotedUTF8Test {
    @Test
    public void testAsUnquotedUTF8_CachesResult() throws Exception {
        String testValue = "Hello, World!";
        SerializedString serializedString = new SerializedString(testValue);

        // First call should encode and cache
        byte[] firstResult = serializedString.asUnquotedUTF8();
        assertNotNull(firstResult);
        assertEquals(testValue.length(), firstResult.length);

        // Second call should return cached value
        byte[] secondResult = serializedString.asUnquotedUTF8();
        assertSame(firstResult, secondResult);
    }

    @Test
    public void testAsUnquotedUTF8_EncodesCorrectly() throws Exception {
        String testValue = "UTF-8 Test: ñ, ü, ç, é";
        SerializedString serializedString = new SerializedString(testValue);

        byte[] result = serializedString.asUnquotedUTF8();
        assertNotNull(result);

        // Verify that the encoded bytes match the UTF-8 encoding of the string
        byte[] expectedBytes = testValue.getBytes("UTF-8");
        assertArrayEquals(expectedBytes, result);
    }

    @Test
    public void testAsUnquotedUTF8_InitialValueIsNull() throws Exception {
        String testValue = "Initial null check";
        SerializedString serializedString = new SerializedString(testValue);

        assertNull(serializedString._unquotedUTF8Ref);

        byte[] result = serializedString.asUnquotedUTF8();
        assertNotNull(result);
        assertNotNull(serializedString._unquotedUTF8Ref);
        assertSame(result, serializedString._unquotedUTF8Ref);
    }
}
