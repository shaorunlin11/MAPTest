package org.apache.commons.codec.digest;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.security.MessageDigest;

public class DigestUtilsgetShaDigestTest {

    @Test
    public void testGetShaDigestReturnsSameAsGetSha1Digest() throws Exception {
        MessageDigest shaDigest = DigestUtils.getShaDigest();
        MessageDigest sha1Digest = DigestUtils.getSha1Digest();
        assertEquals(shaDigest.getAlgorithm(), sha1Digest.getAlgorithm());
    }

    @Test
    public void testGetShaDigestIsDeprecated() throws Exception {
        Method method = DigestUtils.class.getMethod("getShaDigest");
        assertTrue(method.isAnnotationPresent(Deprecated.class));
    }
}
