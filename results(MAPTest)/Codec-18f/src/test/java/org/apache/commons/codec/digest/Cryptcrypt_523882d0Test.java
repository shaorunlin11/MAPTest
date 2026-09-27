package org.apache.commons.codec.digest;

import org.junit.Test;
import static org.junit.Assert.*;

import org.apache.commons.codec.Charsets;

public class Cryptcrypt_523882d0Test {

    @Test
    public void testCryptWithNullSalt() {
        byte[] keyBytes = "testKey".getBytes(Charsets.UTF_8);
        String result = Crypt.crypt((byte[]) keyBytes);
        assertNotNull("Result should not be null", result);
    }

    @Test
    public void testCryptWithEmptyKeyBytes() {
        byte[] keyBytes = new byte[0];
        String result = Crypt.crypt((byte[]) keyBytes);
        assertNotNull("Result should not be null", result);
    }

    @Test
    public void testCryptWithNullKeyBytes() {
        try {
            Crypt.crypt((byte[]) null);
            fail("Expected NullPointerException to be thrown");
        } catch (NullPointerException e) {
            // Expected exception
        }
    }
}
