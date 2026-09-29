package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Assert;

public class DigestUtilsSha512HexZeroCoverageTest {
    @Test
    public void testSha512Hex() {
        byte[] data = "test".getBytes();
        String result = DigestUtils.sha512Hex(data);
        // Ensure the result is not null and has the expected length for SHA-512 hex
        Assert.assertNotNull(result);
        Assert.assertEquals(128, result.length());
    }
}
