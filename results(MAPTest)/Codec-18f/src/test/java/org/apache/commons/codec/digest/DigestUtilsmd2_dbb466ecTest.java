package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtilsmd2_dbb466ecTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testMd2WithNonEmptyData() throws NoSuchAlgorithmException {
        byte[] data = "Hello, World!".getBytes();
        byte[] result = DigestUtils.md2(data);
        Assert.assertNotNull("MD2 hash should not be null", result);
        Assert.assertTrue("MD2 hash should have non-zero length", result.length > 0);
    }

    @Test
    public void testMd2WithEmptyData() {
        byte[] data = new byte[0];
        byte[] result = DigestUtils.md2(data);
        Assert.assertNotNull("MD2 hash of empty data should not be null", result);
        Assert.assertTrue("MD2 hash of empty data should have non-zero length", result.length > 0);
    }

    @Test
    public void testMd2WithNullData() {
        byte[] data = null;
        exception.expect(NullPointerException.class);
        DigestUtils.md2(data);
    }
}
