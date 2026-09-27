package org.apache.commons.codec.digest;

import org.junit.Test;
import static org.junit.Assert.*;

public class Cryptcrypt_c07ef8a4Test {

    @Test
    public void testCryptWithNonNullKey() {
        String key = "testKey";
        String result = Crypt.crypt(key.getBytes());
        assertNotNull("Result should not be null", result);
        assertFalse("Result should not be empty", result.isEmpty());
    }

    @Test
    public void testCryptWithNullKey() {
        try {
            Crypt.crypt((String) null);
            fail("Expected NullPointerException to be thrown");
        } catch (NullPointerException e) {
            // Expected exception
        }
    }
}
