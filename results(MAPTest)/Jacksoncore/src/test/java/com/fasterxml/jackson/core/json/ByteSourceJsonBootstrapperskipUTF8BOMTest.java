package com.fasterxml.jackson.core.json;

import org.junit.Test;
import org.junit.Assert;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;

public class ByteSourceJsonBootstrapperskipUTF8BOMTest {

    @Test
    public void testSkipUTF8BOM_ValidBOM() throws IOException {
        byte[] inputBytes = { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF, (byte) 0x41 };
        InputStream inputStream = new ByteArrayInputStream(inputBytes);
        DataInputStream dataInput = new DataInputStream(inputStream);

        int result = ByteSourceJsonBootstrapper.skipUTF8BOM(dataInput);
        Assert.assertEquals('A', result);
    }

    @Test
    public void testSkipUTF8BOM_MissingFirstByte() throws IOException {
        byte[] inputBytes = { (byte) 0x41, (byte) 0xBB, (byte) 0xBF };
        InputStream inputStream = new ByteArrayInputStream(inputBytes);
        DataInputStream dataInput = new DataInputStream(inputStream);

        int result = ByteSourceJsonBootstrapper.skipUTF8BOM(dataInput);
        Assert.assertEquals('A', result);
    }

    @Test
    public void testSkipUTF8BOM_MissingSecondByte() throws IOException {
        byte[] inputBytes = { (byte) 0xEF, (byte) 0x41, (byte) 0xBF };
        InputStream inputStream = new ByteArrayInputStream(inputBytes);
        DataInputStream dataInput = new DataInputStream(inputStream);

        try {
            ByteSourceJsonBootstrapper.skipUTF8BOM(dataInput);
            Assert.fail("Expected IOException");
        } catch (IOException e) {
            Assert.assertTrue(e.getMessage().contains("0x41"));
        }
    }

    @Test
    public void testSkipUTF8BOM_MissingThirdByte() throws IOException {
        byte[] inputBytes = { (byte) 0xEF, (byte) 0xBB, (byte) 0x41 };
        InputStream inputStream = new ByteArrayInputStream(inputBytes);
        DataInputStream dataInput = new DataInputStream(inputStream);

        try {
            ByteSourceJsonBootstrapper.skipUTF8BOM(dataInput);
            Assert.fail("Expected IOException");
        } catch (IOException e) {
            Assert.assertTrue(e.getMessage().contains("0x41"));
        }
    }
}
