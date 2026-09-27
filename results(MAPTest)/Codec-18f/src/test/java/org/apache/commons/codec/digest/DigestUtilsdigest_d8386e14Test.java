package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.ByteBuffer;

public class DigestUtilsdigest_d8386e14Test {
    private DigestUtils digestUtils;

    @Before
    public void setUp() throws NoSuchAlgorithmException {
        digestUtils = new DigestUtils(MessageDigest.getInstance("SHA-256"));
    }

    @After
    public void tearDown() {
        digestUtils = null;
    }

    @Test
    public void testDigestWithByteBuffer() {
        byte[] inputBytes = "test".getBytes();
        ByteBuffer data = ByteBuffer.wrap(inputBytes);
        byte[] result = digestUtils.digest(data);
        Assert.assertNotNull("Result should not be null", result);
        Assert.assertTrue("Result should have non-zero length", result.length > 0);
    }
}
