package org.apache.commons.codec.digest;

import org.junit.Test;
import static org.junit.Assert.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtilsgetMd2DigestTest {

    @Test
    public void testGetMd2Digest() throws NoSuchAlgorithmException {
        MessageDigest md2Digest = DigestUtils.getMd2Digest();
        assertNotNull("MessageDigest instance should not be null", md2Digest);
        assertEquals("MessageDigest algorithm name should be MD2", "MD2", md2Digest.getAlgorithm());
    }
}
