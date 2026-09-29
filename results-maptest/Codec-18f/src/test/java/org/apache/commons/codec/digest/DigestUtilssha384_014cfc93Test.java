package org.apache.commons.codec.digest;

import org.junit.Test;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class DigestUtilssha384_014cfc93Test {

    @Test
    public void testSha384WithNonNullData() throws NoSuchAlgorithmException {
        byte[] input = "Hello, World!".getBytes();
        byte[] expected = MessageDigest.getInstance("SHA-384").digest(input);
        byte[] actual = DigestUtils.sha384(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSha384WithEmptyData() throws NoSuchAlgorithmException {
        byte[] input = new byte[0];
        byte[] expected = MessageDigest.getInstance("SHA-384").digest(input);
        byte[] actual = DigestUtils.sha384(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSha384OutputLength() throws NoSuchAlgorithmException {
        byte[] input = "Test".getBytes();
        byte[] actual = DigestUtils.sha384(input);
        assertEquals(48, actual.length);
    }
}
