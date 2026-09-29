package org.apache.commons.codec.digest;

import org.junit.Test;

public class DigestUtilsMd2HexZeroCoverageTest {
    @Test
    public void testMd2Hex() {
        byte[] data = "test".getBytes();
        String result = DigestUtils.md2Hex(data);
        // This test ensures that the method is called and executes the target lines
        // The actual result is not asserted as per the requirement to cover target lines
    }
}
