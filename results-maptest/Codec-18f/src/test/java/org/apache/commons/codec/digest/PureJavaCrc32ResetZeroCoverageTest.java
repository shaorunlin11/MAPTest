package org.apache.commons.codec.digest;

import org.junit.Test;

public class PureJavaCrc32ResetZeroCoverageTest {
    @Test
    public void testReset() {
        PureJavaCrc32 crc32 = new PureJavaCrc32();
        crc32.reset();
    }
}
