package org.apache.commons.codec.digest;

import org.junit.Test;

public class DigestUtilsDigestZeroCoverage_167Test {
    @Test
    public void testDigestWithNonNullData() {
        DigestUtils digestUtils = new DigestUtils("MD5");
        String data = "testData";
        byte[] result = digestUtils.digest(data);
        // Ensure the method is called and returns a non-null value
        assert result != null;
    }
}
