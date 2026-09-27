package org.apache.commons.codec.digest;

import org.junit.Test;
import static org.junit.Assert.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.ByteBuffer;

public class DigestUtilsupdateDigest_fd53c5dbTest {

    @Test
    public void testUpdateDigest() throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        ByteBuffer valueToDigest = ByteBuffer.wrap("test".getBytes());

        MessageDigest result = DigestUtils.updateDigest(messageDigest, valueToDigest);

        assertNotNull(result);
        assertEquals(messageDigest, result);
    }
}
