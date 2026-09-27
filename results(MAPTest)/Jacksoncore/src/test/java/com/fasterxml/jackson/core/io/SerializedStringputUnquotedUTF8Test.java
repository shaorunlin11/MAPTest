package com.fasterxml.jackson.core.io;

import org.junit.Test;
import org.junit.Assert;
import java.nio.ByteBuffer;
import com.fasterxml.jackson.core.util.BufferRecyclers;
import com.fasterxml.jackson.core.SerializableString;

public class SerializedStringputUnquotedUTF8Test {

    @Test
    public void testPutUnquotedUTF8WithSufficientBuffer() throws Exception {
        SerializedString serializedString = new SerializedString("Hello, World!");
        ByteBuffer buffer = ByteBuffer.allocate(14); // "Hello, World!" is 13 bytes in UTF-8

        int result = serializedString.putUnquotedUTF8(buffer);

        Assert.assertEquals(13, result);
        Assert.assertEquals("Hello, World!", new String(buffer.array(), 0, result));
    }

    @Test
    public void testPutUnquotedUTF8WithInsufficientBuffer() throws Exception {
        SerializedString serializedString = new SerializedString("Hello, World!");
        ByteBuffer buffer = ByteBuffer.allocate(10); // Not enough for "Hello, World!" (13 bytes)

        int result = serializedString.putUnquotedUTF8(buffer);

        Assert.assertEquals(-1, result);
    }

    @Test
    public void testPutUnquotedUTF8LazyInitialization() throws Exception {
        SerializedString serializedString = new SerializedString("Test");
        ByteBuffer buffer = ByteBuffer.allocate(5);

        int result = serializedString.putUnquotedUTF8(buffer);

        Assert.assertEquals(4, result);
        Assert.assertEquals("Test", new String(buffer.array(), 0, result));
    }

    @Test
    public void testPutUnquotedUTF8WithNullValue() throws Exception {
        try {
            new SerializedString(null);
            Assert.fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            Assert.assertTrue(e.getMessage().contains("Null String illegal for SerializedString"));
        }
    }
}
