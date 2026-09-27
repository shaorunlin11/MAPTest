package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtilsupdateDigest_b5015957Test {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testUpdateDigestWithNonNullDigestAndData() throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        String testData = "Hello, world!";
        InputStream data = new ByteArrayInputStream(testData.getBytes());

        MessageDigest result = DigestUtils.updateDigest(digest, data);

        Assert.assertNotNull("Resulting digest should not be null", result);
        Assert.assertSame("Resulting digest should be the same instance as input", digest, result);
    }

    @Test
    public void testUpdateDigestWithEmptyInputStream() throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        InputStream data = new ByteArrayInputStream(new byte[0]);

        MessageDigest result = DigestUtils.updateDigest(digest, data);

        Assert.assertNotNull("Resulting digest should not be null", result);
        Assert.assertSame("Resulting digest should be the same instance as input", digest, result);
    }

    @Test
    public void testUpdateDigestWithIOException() throws Exception {
        thrown.expect(IOException.class);
        thrown.expectMessage("Simulated IOException");

        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        InputStream data = new InputStream() {
            @Override
            public int read() throws IOException {
                throw new IOException("Simulated IOException");
            }
        };

        DigestUtils.updateDigest(digest, data);
    }
}
