package org.apache.commons.codec.digest;

import org.junit.Test;

public class PureJavaCrc32UpdateZeroCoverage_239Test {
    @Test
    public void testUpdate() {
        PureJavaCrc32 crc32 = new PureJavaCrc32();
        int b = 0;
        crc32.update(b);
    }
}
