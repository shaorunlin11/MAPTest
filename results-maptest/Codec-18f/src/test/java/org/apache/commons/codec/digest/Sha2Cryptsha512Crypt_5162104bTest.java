package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Assert;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.codec.Charsets;

public class Sha2Cryptsha512Crypt_5162104bTest {

    private static final String SHA256_PREFIX = "$5$";

    @Test
    public void testSha512Crypt_withNonNullKey() throws NoSuchAlgorithmException {
        byte[] keyBytes = "test".getBytes(Charsets.UTF_8);
        String result = Sha2Crypt.sha512Crypt(keyBytes);
        // Verify that the method delegates to the overloaded version with null salt
        // This test assumes the overloaded method is implemented and returns a valid hash
        // The actual hash value is not verified here as it depends on the implementation
        // We only verify that the method returns a non-null string
        Assert.assertNotNull(result);
    }

    @Test
    public void testSha512Crypt_withNonEmptyKey() throws NoSuchAlgorithmException {
        byte[] keyBytes = "securePassword123".getBytes(Charsets.UTF_8);
        String result = Sha2Crypt.sha512Crypt(keyBytes);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.startsWith("$6$")); // Changed from "$5$" to "$6$" for SHA-512
    }

    @Test
    public void testSha512Crypt_withEmptyKey() throws NoSuchAlgorithmException {
        byte[] keyBytes = new byte[0];
        String result = Sha2Crypt.sha512Crypt(keyBytes);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.startsWith("$6$")); // Changed from "$5$" to "$6$" for SHA-512
    }

    @Test
    public void testSha512Crypt_withNullKey() {
        try {
            Sha2Crypt.sha512Crypt(null);
            // This should not reach here as the method is marked final and does not handle null
            // But since the method does not explicitly check for null, it may throw an exception
            // depending on the implementation of the overloaded method
            // This test is a placeholder to indicate that null input may cause issues
            Assert.fail("Expected NullPointerException to be thrown");
        } catch (NullPointerException e) {
            // Expected exception
        } catch (Exception e) {
            Assert.fail("Unexpected exception: " + e.getMessage());
        }
    }
}
