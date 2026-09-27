package org.apache.commons.codec.digest;

import org.junit.Test;
import static org.junit.Assert.*;

public class DigestUtilsSha1ZeroCoverage_134Test {
    @Test
    public void testSha1WithNonEmptyString() {
        String data = "testData";
        byte[] result = DigestUtils.sha1(data);
        assertNotNull("Result should not be null", result);
        assertTrue("Result should not be empty", result.length > 0);
    }
}
