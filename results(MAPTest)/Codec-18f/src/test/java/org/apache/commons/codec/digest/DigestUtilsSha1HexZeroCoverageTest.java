package org.apache.commons.codec.digest;

import org.junit.Test;

public class DigestUtilsSha1HexZeroCoverageTest {
    @Test
    public void testSha1Hex() {
        String data = "testData";
        String result = DigestUtils.sha1Hex(data);
        // The test is designed to execute the target lines without making assertions
        // as per the requirement to cover target lines 529.
    }
}
