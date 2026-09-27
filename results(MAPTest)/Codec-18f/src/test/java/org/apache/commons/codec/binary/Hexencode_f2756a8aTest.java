package org.apache.commons.codec.binary;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Hexencode_f2756a8aTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testEncode() throws Exception {
        Hex hex = new Hex();
        byte[] input = {0x48, 0x65, 0x6c, 0x6c, 0x6f};
        byte[] result = hex.encode(input);

        String expectedHex = "48656c6c6f";
        byte[] expectedBytes = expectedHex.getBytes(hex.getCharset());

        Assert.assertArrayEquals(expectedBytes, result);
    }

    @Test
    public void testEncodeWithCustomCharset() throws Exception {
        Hex hex = new Hex(StandardCharsets.US_ASCII);
        byte[] input = {0x48, 0x65, 0x6c, 0x6c, 0x6f};
        byte[] result = hex.encode(input);

        String expectedHex = "48656c6c6f";
        byte[] expectedBytes = expectedHex.getBytes(hex.getCharset());

        Assert.assertArrayEquals(expectedBytes, result);
    }

    @Test
    public void testEncodeNullInput() throws Exception {
        Hex hex = new Hex();
        byte[] input = null;
        thrown.expect(NullPointerException.class);
        hex.encode(input);
    }
}
