package org.apache.commons.codec.digest;

import org.junit.Test;
import static org.junit.Assert.*;

public class UnixCryptcrypt_8171e216Test {

    @Test
    public void testCryptWithNullSalt() {
        byte[] original = "test".getBytes();
        String result = UnixCrypt.crypt(original);
        assertNotNull("Result should not be null", result);
        assertFalse("Result should not be empty", result.isEmpty());
    }

    @Test
    public void testCryptWithEmptyInput() {
        byte[] original = new byte[0];
        String result = UnixCrypt.crypt(original);
        assertNotNull("Result should not be null", result);
        assertFalse("Result should not be empty", result.isEmpty());
    }

    @Test
    public void testCryptWithNonNullInput() {
        byte[] original = "hello".getBytes();
        String result = UnixCrypt.crypt(original);
        assertNotNull("Result should not be null", result);
        assertFalse("Result should not be empty", result.isEmpty());
    }
}
