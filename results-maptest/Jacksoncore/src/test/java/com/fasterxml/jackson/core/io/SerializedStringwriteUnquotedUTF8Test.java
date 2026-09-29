package com.fasterxml.jackson.core.io;

import org.junit.Test;
import org.junit.Assert;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class SerializedStringwriteUnquotedUTF8Test {

    @Test
    public void testWriteUnquotedUTF8() throws Exception {
        String testValue = "Hello, World!";
        SerializedString serializedString = new SerializedString(testValue);

        OutputStream out = new ByteArrayOutputStream();
        int length = serializedString.writeUnquotedUTF8(out);

        Assert.assertEquals(testValue.getBytes().length, length);
        Assert.assertTrue(((ByteArrayOutputStream) out).toString().equals(testValue));
    }

    @Test
    public void testWriteUnquotedUTF8WithNullReference() throws Exception {
        String testValue = "Test";
        SerializedString serializedString = new SerializedString(testValue);

        // Clear the cached reference to force encoding
        serializedString._unquotedUTF8Ref = null;

        OutputStream out = new ByteArrayOutputStream();
        int length = serializedString.writeUnquotedUTF8(out);

        Assert.assertEquals(testValue.getBytes().length, length);
        Assert.assertTrue(((ByteArrayOutputStream) out).toString().equals(testValue));
    }

    @Test
    public void testWriteUnquotedUTF8WithIOException() throws Exception {
        String testValue = "Test";
        SerializedString serializedString = new SerializedString(testValue);

        OutputStream out = new OutputStream() {
            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                throw new IOException("Simulated IO exception");
            }

            @Override
            public void close() throws IOException {
                // Do nothing
            }

            @Override
            public void flush() throws IOException {
                // Do nothing
            }

            @Override
            public void write(int b) throws IOException {
                // Do nothing
            }

            @Override
            public void write(byte[] b) throws IOException {
                // Do nothing
            }
        };

        try {
            serializedString.writeUnquotedUTF8(out);
            Assert.fail("Expected IOException was not thrown");
        } catch (IOException e) {
            // Expected exception
        }
    }
}
