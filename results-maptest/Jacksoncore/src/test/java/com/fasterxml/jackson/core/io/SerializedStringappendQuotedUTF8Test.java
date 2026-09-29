package com.fasterxml.jackson.core.io;

import org.junit.Test;
import org.junit.Assert;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class SerializedStringappendQuotedUTF8Test {

    @Test
    public void testAppendQuotedUTF8WithSufficientBuffer() throws Exception {
        SerializedString serializedString = new SerializedString("Hello, World!");
        byte[] buffer = new byte[100];
        int offset = 0;
        int result = serializedString.appendQuotedUTF8(buffer, offset);
        Assert.assertTrue(result > 0);
        Assert.assertEquals("Hello, World!", new String(buffer, 0, result));
    }

    @Test
    public void testAppendQuotedUTF8WithInsufficientBuffer() throws Exception {
        SerializedString serializedString = new SerializedString("Hello, World!");
        byte[] buffer = new byte[5];
        int offset = 0;
        int result = serializedString.appendQuotedUTF8(buffer, offset);
        Assert.assertEquals(-1, result);
    }

    @Test
    public void testAppendQuotedUTF8WithOffset() throws Exception {
        SerializedString serializedString = new SerializedString("Hello, World!");
        byte[] buffer = new byte[20];
        int offset = 5;
        int result = serializedString.appendQuotedUTF8(buffer, offset);
        Assert.assertTrue(result > 0);
        Assert.assertEquals("Hello, World!", new String(buffer, offset, result));
    }

    @Test
    public void testAppendQuotedUTF8WithNullValue() {
        try {
            new SerializedString(null);
            Assert.fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            // Expected
        }
    }
}
