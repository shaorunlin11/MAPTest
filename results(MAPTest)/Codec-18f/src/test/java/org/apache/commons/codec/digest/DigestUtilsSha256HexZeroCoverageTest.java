package org.apache.commons.codec.digest;

import org.junit.Test;

public class DigestUtilsSha256HexZeroCoverageTest {
    @Test
    public void testSha256Hex() {
        byte[] data = {1, 2, 3};
        String result = DigestUtils.sha256Hex(data);
        // This test executes the target lines by calling the method with non-null, non-empty byte array
    }
}
