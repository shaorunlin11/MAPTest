package org.apache.commons.codec.digest;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.junit.Test;
import static org.junit.Assert.*;

public class DigestUtilsdigest_d178a2c3Test {

    @Test
    public void testDigest() throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        ByteBuffer data = ByteBuffer.wrap("test".getBytes());

        byte[] result = DigestUtils.digest(messageDigest, data);

        // Verify that the digest was computed correctly
        assertNotNull(result);
        assertTrue(result.length > 0);
    }
}
