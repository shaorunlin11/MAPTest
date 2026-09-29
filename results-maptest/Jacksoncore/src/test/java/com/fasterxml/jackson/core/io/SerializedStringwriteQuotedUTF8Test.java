package com.fasterxml.jackson.core.io;

import org.junit.Test;
import org.junit.Assert;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import com.fasterxml.jackson.core.util.BufferRecyclers;

public class SerializedStringwriteQuotedUTF8Test {
    @Test
    public void testWriteQuotedUTF8() throws IOException {
        String testValue = "Hello, World!";
        SerializedString serializedString = new SerializedString(testValue);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int bytesWritten = serializedString.writeQuotedUTF8(outputStream);

        byte[] expectedBytes = BufferRecyclers.quoteAsJsonUTF8(testValue);
        Assert.assertEquals(expectedBytes.length, bytesWritten);
        Assert.assertArrayEquals(expectedBytes, outputStream.toByteArray());
    }
}
