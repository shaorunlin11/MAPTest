package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class HmacUtilshmacSha384_4e314dc0Test {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testHmacSha384WithValidKeyAndInputStream() throws IOException {
        byte[] key = "secret".getBytes();
        String input = "Hello, world!";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());

        byte[] result = HmacUtils.hmacSha384(key, inputStream);

        Assert.assertNotNull("Result should not be null", result);
        Assert.assertTrue("Result should have non-zero length", result.length > 0);
    }

    @Test
    public void testHmacSha384WithNullKey() throws IOException {
        thrown.expect(IllegalArgumentException.class);
        byte[] key = null;
        InputStream inputStream = new ByteArrayInputStream("test".getBytes());

        HmacUtils.hmacSha384(key, inputStream);
    }

    @Test
    public void testHmacSha384WithNullInputStream() throws IOException {
        thrown.expect(NullPointerException.class);
        byte[] key = "secret".getBytes();
        InputStream inputStream = null;

        HmacUtils.hmacSha384(key, inputStream);
    }

    @Test
    public void testHmacSha384WithEmptyInputStream() throws IOException {
        byte[] key = "secret".getBytes();
        InputStream inputStream = new ByteArrayInputStream(new byte[0]);

        byte[] result = HmacUtils.hmacSha384(key, inputStream);

        Assert.assertNotNull("Result should not be null", result);
        Assert.assertTrue("Result should have non-zero length", result.length > 0);
    }
}
