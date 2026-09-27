package com.fasterxml.jackson.core.io;

import org.junit.Test;
import org.junit.Assert;
import java.nio.ByteBuffer;
import com.fasterxml.jackson.core.util.BufferRecyclers;

public class SerializedStringputQuotedUTF8Test {
    @Test
    public void testPutQuotedUTF8WithSufficientBuffer() throws Exception {
        String testValue = "Hello, World!";
        SerializedString serializedString = new SerializedString(testValue);

        ByteBuffer buffer = ByteBuffer.allocate(100);
        int result = serializedString.putQuotedUTF8(buffer);

        Assert.assertTrue("Should return a positive length", result > 0);
        Assert.assertEquals("Should have written the correct number of bytes", result, buffer.position());
    }

    @Test
    public void testPutQuotedUTF8WithInsufficientBuffer() throws Exception {
        String testValue = "Hello, World!";
        SerializedString serializedString = new SerializedString(testValue);

        ByteBuffer buffer = ByteBuffer.allocate(5);
        int result = serializedString.putQuotedUTF8(buffer);

        Assert.assertEquals("Should return -1 for insufficient buffer", -1, result);
        Assert.assertEquals("Should not have written any bytes", 0, buffer.position());
    }

    @Test
    public void testPutQuotedUTF8Caching() throws Exception {
        String testValue = "Cached Value";
        SerializedString serializedString = new SerializedString(testValue);

        ByteBuffer buffer1 = ByteBuffer.allocate(100);
        int result1 = serializedString.putQuotedUTF8(buffer1);

        ByteBuffer buffer2 = ByteBuffer.allocate(100);
        int result2 = serializedString.putQuotedUTF8(buffer2);

        Assert.assertEquals("Should return the same length after caching", result1, result2);
        Assert.assertArrayEquals("Should have cached the same byte array", buffer1.array(), buffer2.array());
    }
}
