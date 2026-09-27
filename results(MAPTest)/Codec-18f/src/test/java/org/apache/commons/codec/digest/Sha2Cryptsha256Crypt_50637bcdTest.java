package org.apache.commons.codec.digest;

import org.junit.Test;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.codec.Charsets;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class Sha2Cryptsha256Crypt_50637bcdTest {

    private static final String SHA256_PREFIX = "$5$";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testSha256CryptWithNullSalt() throws NoSuchAlgorithmException {
        byte[] keyBytes = "testPassword".getBytes(Charsets.UTF_8);
        String result = Sha2Crypt.sha256Crypt(keyBytes);
        assertNotNull("Result should not be null", result);
        assertTrue("Result should start with SHA256 prefix", result.startsWith(SHA256_PREFIX));
    }

    @Test
    public void testSha256CryptWithEmptyKey() {
        byte[] keyBytes = new byte[0];
        String result = Sha2Crypt.sha256Crypt(keyBytes);
        assertNotNull("Result should not be null", result);
        assertTrue("Result should start with SHA256 prefix", result.startsWith(SHA256_PREFIX));
    }

    @Test
    public void testSha256CryptWithNullKeyBytes() {
        thrown.expect(NullPointerException.class);
        Sha2Crypt.sha256Crypt(null);
    }
}
