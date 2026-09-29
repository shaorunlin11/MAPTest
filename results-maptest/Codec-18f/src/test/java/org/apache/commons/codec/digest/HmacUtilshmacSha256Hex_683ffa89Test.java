package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class HmacUtilshmacSha256Hex_683ffa89Test {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testHmacSha256Hex_withValidInput_returnsHexDigest() throws IOException {
        byte[] key = "secret".getBytes();
        String input = "test data";
        InputStream valueToDigest = new ByteArrayInputStream(input.getBytes());

        String result = HmacUtils.hmacSha256Hex(key, valueToDigest);

        Assert.assertNotNull(result);
        Assert.assertTrue(result.matches("[0-9a-fA-F]+"));
    }

    @Test
    public void testHmacSha256Hex_withNullKey_throwsNullPointerException() throws IOException {
        byte[] key = null;
        InputStream valueToDigest = new ByteArrayInputStream("test".getBytes());

        thrown.expect(IllegalArgumentException.class);
        HmacUtils.hmacSha256Hex(key, valueToDigest);
    }

    @Test
    public void testHmacSha256Hex_withNullInputStream_throwsNullPointerException() throws IOException {
        byte[] key = "secret".getBytes();
        InputStream valueToDigest = null;

        thrown.expect(NullPointerException.class);
        HmacUtils.hmacSha256Hex(key, valueToDigest);
    }

    @Test
    public void testHmacSha256Hex_withEmptyInputStream_returnsCorrectHex() throws IOException {
        byte[] key = "secret".getBytes();
        InputStream valueToDigest = new ByteArrayInputStream(new byte[0]);

        String result = HmacUtils.hmacSha256Hex(key, valueToDigest);

        Assert.assertNotNull(result);
        Assert.assertTrue(result.matches("[0-9a-fA-F]+"));
    }
}
