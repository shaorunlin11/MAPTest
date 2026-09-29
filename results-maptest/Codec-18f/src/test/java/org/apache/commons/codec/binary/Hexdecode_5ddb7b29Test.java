package org.apache.commons.codec.binary;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.DecoderException;

public class Hexdecode_5ddb7b29Test {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testDecodeValidHex() throws Exception {
        Hex hex = new Hex();
        byte[] input = "48656c6c6f".getBytes(StandardCharsets.UTF_8);
        byte[] result = hex.decode(input);
        Assert.assertArrayEquals("Hello".getBytes(StandardCharsets.UTF_8), result);
    }

    @Test
    public void testDecodeWithCustomCharset() throws Exception {
        Hex hex = new Hex(StandardCharsets.US_ASCII);
        byte[] input = "48656c6c6f".getBytes(StandardCharsets.US_ASCII);
        byte[] result = hex.decode(input);
        Assert.assertArrayEquals("Hello".getBytes(StandardCharsets.US_ASCII), result);
    }

    @Test
    public void testDecodeEmptyArray() throws Exception {
        Hex hex = new Hex();
        byte[] input = new byte[0];
        byte[] result = hex.decode(input);
        Assert.assertEquals(0, result.length);
    }

    @Test
    public void testDecodeInvalidHex() throws Exception {
        Hex hex = new Hex();
        byte[] input = "48656c6c".getBytes(StandardCharsets.UTF_8); // Odd length
        byte[] result = hex.decode(input);
        Assert.assertNotEquals("Hello".getBytes(StandardCharsets.UTF_8), result);
    }

    @Test
    public void testDecodeNonHexCharacters() throws Exception {
        Hex hex = new Hex();
        byte[] input = "48656c6c7a".getBytes(StandardCharsets.UTF_8); // 'z' is not a valid hex character
        byte[] result = hex.decode(input);
        Assert.assertNotEquals("Helloz".getBytes(StandardCharsets.UTF_8), result);
    }
}
