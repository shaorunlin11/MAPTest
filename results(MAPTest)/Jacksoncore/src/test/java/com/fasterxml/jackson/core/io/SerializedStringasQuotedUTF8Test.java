package com.fasterxml.jackson.core.io;

import org.junit.Test;
import org.junit.Assert;

import java.io.Serializable;

public class SerializedStringasQuotedUTF8Test {
    @Test
    public void testAsQuotedUTF8_CachesResult() throws Exception {
        String testValue = "Hello, World!";
        SerializedString serializedString = new SerializedString(testValue);

        // First call should compute and cache
        byte[] firstResult = serializedString.asQuotedUTF8();
        Assert.assertNotNull(firstResult);
        Assert.assertEquals(testValue.getBytes("UTF-8").length, firstResult.length);

        // Second call should return cached value
        byte[] secondResult = serializedString.asQuotedUTF8();
        Assert.assertSame(firstResult, secondResult);
    }

    @Test
    public void testAsQuotedUTF8_UsesBufferRecyclers() throws Exception {
        String testValue = "Test String";
        SerializedString serializedString = new SerializedString(testValue);

        byte[] result = serializedString.asQuotedUTF8();
        Assert.assertNotNull(result);
        Assert.assertEquals(testValue.getBytes("UTF-8").length, result.length);
    }
}
