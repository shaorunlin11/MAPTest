package org.apache.commons.codec.digest;

import org.junit.Test;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertArrayEquals;

public class DigestUtilsdigest_59212df7Test {

    @Test
    public void testDigest() throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] data = "test".getBytes();
        byte[] result = DigestUtils.digest(md, data);
        byte[] expected = md.digest(data);
        assertArrayEquals(expected, result);
    }
}
