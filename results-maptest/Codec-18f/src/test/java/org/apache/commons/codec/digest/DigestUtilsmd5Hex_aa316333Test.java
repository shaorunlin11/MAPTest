package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Assert;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtilsmd5Hex_aa316333Test {

    @Test
    public void testMd5HexWithNonNullData() throws NoSuchAlgorithmException {
        byte[] data = "test".getBytes();
        String result = DigestUtils.md5Hex(data);
        Assert.assertNotNull(result);
        Assert.assertFalse(result.isEmpty());
    }

    @Test
    public void testMd5HexWithNullData() {
        byte[] data = null;
        try {
            String result = DigestUtils.md5Hex(data);
            Assert.assertNull(result);
        } catch (NullPointerException e) {
            // Expected exception, test passes
        }
    }

    @Test
    public void testMd5HexWithEmptyData() {
        byte[] data = new byte[0];
        String result = DigestUtils.md5Hex(data);
        Assert.assertNotNull(result);
        Assert.assertFalse(result.isEmpty());
    }

    @Test
    public void testMd5HexWithDifferentData() throws NoSuchAlgorithmException {
        byte[] data1 = "hello".getBytes();
        byte[] data2 = "world".getBytes();
        String result1 = DigestUtils.md5Hex(data1);
        String result2 = DigestUtils.md5Hex(data2);
        Assert.assertNotEquals(result1, result2);
    }
}
