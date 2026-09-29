package org.apache.commons.codec.binary;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.apache.commons.codec.DecoderException;

public class Hexdecode_e7e89d38Test {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testDecodeString() throws Exception {
        Hex hex = new Hex();
        byte[] result = (byte[]) hex.decode("48656c6c6f");
        Assert.assertArrayEquals(new byte[]{0x48, 0x65, 0x6c, 0x6c, 0x6f}, result);
    }

    @Test
    public void testDecodeByteArray() throws Exception {
        Hex hex = new Hex();
        byte[] result = (byte[]) hex.decode("48656c6c6f");
        Assert.assertArrayEquals(new byte[]{0x48, 0x65, 0x6c, 0x6c, 0x6f}, result);
    }

    @Test
    public void testDecodeByteBuffer() throws Exception {
        Hex hex = new Hex();
        ByteBuffer buffer = ByteBuffer.wrap("48656c6c6f".getBytes());
        byte[] result = (byte[]) hex.decode(buffer);
        Assert.assertArrayEquals(new byte[]{0x48, 0x65, 0x6c, 0x6c, 0x6f}, result);
    }

    @Test
    public void testDecodeCharArray() throws Exception {
        Hex hex = new Hex();
        char[] chars = "48656c6c6f".toCharArray();
        byte[] result = (byte[]) hex.decode(chars);
        Assert.assertArrayEquals(new byte[]{0x48, 0x65, 0x6c, 0x6c, 0x6f}, result);
    }

    @Test
    public void testDecodeUnsupportedType() throws Exception {
        Hex hex = new Hex();
        thrown.expect(DecoderException.class);
        thrown.expectMessage("java.lang.Integer cannot be cast to [C");
        hex.decode(123);
    }

    @Test
    public void testDecodeInvalidCharArray() throws Exception {
        Hex hex = new Hex();
        thrown.expect(DecoderException.class);
        thrown.expectMessage("Odd number of characters.");
        hex.decode(new char[]{'g'});
    }

    @Test
    public void testDecodeWithCharset() throws Exception {
        Hex hex = new Hex(Charset.forName("UTF-8"));
        byte[] result = (byte[]) hex.decode("48656c6c6f");
        Assert.assertArrayEquals(new byte[]{0x48, 0x65, 0x6c, 0x6c, 0x6f}, result);
    }
}
