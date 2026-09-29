package org.apache.commons.codec.digest;

import org.junit.Test;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import org.apache.commons.codec.Charsets;

import static org.junit.Assert.*;

public class Sha2Cryptsha256Crypt_77c79edeTest {
    private static final String SHA256_PREFIX = "$5$";

    @Test
    public void testSha256CryptWithNullSalt() throws NoSuchAlgorithmException {
        byte[] keyBytes = "test".getBytes(Charsets.UTF_8);
        String result = Sha2Crypt.sha256Crypt(keyBytes, null);

        assertNotNull("Result should not be null", result);
        assertTrue("Result should start with $5$", result.startsWith(SHA256_PREFIX));
        // Verify that the salt is present and has the correct format
        String[] parts = result.split("\\$", 3);
        assertEquals("Expected 3 parts after splitting by $", 3, parts.length);
        assertEquals("First part should be empty", "", parts[0]);
        assertEquals("Second part should be 5", "5", parts[1]);
    }

    @Test
    public void testSha256CryptWithProvidedSalt() throws NoSuchAlgorithmException {
        byte[] keyBytes = "test".getBytes(Charsets.UTF_8);
        String salt = "$5$abcdef1234567890";
        String result = Sha2Crypt.sha256Crypt(keyBytes, salt);

        assertNotNull("Result should not be null", result);
        assertTrue("Result should start with $5$", result.startsWith(SHA256_PREFIX));
        // Verify that the provided salt is included in the result
        assertTrue("Result should contain the provided salt", result.contains(salt));
    }

    @Test
    public void testSha256CryptWithEmptyKey() throws NoSuchAlgorithmException {
        byte[] keyBytes = new byte[0];
        String result = Sha2Crypt.sha256Crypt(keyBytes, null);

        assertNotNull("Result should not be null", result);
        assertTrue("Result should start with $5$", result.startsWith(SHA256_PREFIX));
    }

    @Test
    public void testSha256CryptWithNonDefaultSalt() throws NoSuchAlgorithmException {
        byte[] keyBytes = "test".getBytes(Charsets.UTF_8);
        String salt = "$5$rounds=1000$abcdefgh";
        String result = Sha2Crypt.sha256Crypt(keyBytes, salt);

        assertNotNull("Result should not be null", result);
        assertTrue("Result should start with $5$", result.startsWith(SHA256_PREFIX));
        assertTrue("Result should contain the provided salt", result.contains(salt));
    }

    @Test
    public void testSha256CryptWithDifferentKey() throws NoSuchAlgorithmException {
        byte[] keyBytes1 = "hello".getBytes(Charsets.UTF_8);
        byte[] keyBytes2 = "world".getBytes(Charsets.UTF_8);
        String salt = "$5$abcdefgh";

        String hash1 = Sha2Crypt.sha256Crypt(keyBytes1, salt);
        String hash2 = Sha2Crypt.sha256Crypt(keyBytes2, salt);

        assertNotEquals("Hashes for different keys should be different", hash1, hash2);
    }
}
