package org.apache.commons.codec.digest;

import org.junit.Test;

public class DigestUtilsSha512ZeroCoverageTest {
    @Test
    public void testSha512() {
        byte[] data = "test".getBytes();
        byte[] result = DigestUtils.sha512(data);
        // This test simply calls the method to ensure it's executed
    }
}
