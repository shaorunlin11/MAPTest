package org.apache.commons.codec.digest;

import org.junit.Test;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.codec.Charsets;
import org.junit.Assert;

public class Md5Cryptmd5Crypt_48673c28Test {

    @Test
    public void testMd5Crypt_withKeyBytes() {
        byte[] keyBytes = "test".getBytes(Charsets.UTF_8);
        String result = Md5Crypt.md5Crypt(keyBytes);
        // Verify that the result is not null and has the expected format
        Assert.assertNotNull(result);
        Assert.assertTrue(result.startsWith(Md5Crypt.MD5_PREFIX));
        // Verify that the salt is 8 characters long (after the prefix)
        int prefixLength = Md5Crypt.MD5_PREFIX.length();
        String saltPart = result.substring(prefixLength);
        Assert.assertEquals(31, saltPart.length());
    }
}
