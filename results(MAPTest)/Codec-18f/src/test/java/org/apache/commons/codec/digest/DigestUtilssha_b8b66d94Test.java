package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertArrayEquals;

public class DigestUtilssha_b8b66d94Test {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testSha() throws NoSuchAlgorithmException {
        byte[] data = "test".getBytes();
        byte[] expected = DigestUtils.sha1(data);
        byte[] actual = DigestUtils.sha(data);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testShaWithNullData() {
        exception.expect(NullPointerException.class);
        byte[] data = null;
        byte[] actual = DigestUtils.sha(data);
    }
}
