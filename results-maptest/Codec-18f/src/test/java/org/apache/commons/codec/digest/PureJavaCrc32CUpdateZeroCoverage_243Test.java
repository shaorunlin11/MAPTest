package org.apache.commons.codec.digest;

import org.junit.Test;

public class PureJavaCrc32CUpdateZeroCoverage_243Test {
    @Test
    public void testUpdate() {
        PureJavaCrc32C crc32c = new PureJavaCrc32C();
        int b = 0;
        crc32c.update(b);
    }
}
