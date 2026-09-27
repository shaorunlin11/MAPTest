package org.apache.commons.codec.digest;

import org.junit.Test;

public class DigestUtilsSha1ZeroCoverageTest {
    @Test
    public void testSha1() {
        byte[] data = "test".getBytes();
        byte[] result = DigestUtils.sha1(data);
        // Ensure the method is called and returns a non-null value
        assert result != null;
    }
}
