package org.apache.commons.codec.digest;

import org.junit.Test;

public class PureJavaCrc32GetValueZeroCoverageTest {
    @Test
    public void testGetValue() {
        PureJavaCrc32 crc = new PureJavaCrc32();
        crc.update(0);
        long value = crc.getValue();
        // The test is designed to execute line 54 of the getValue method.
        // The line is: return (~crc) & 0xffffffffL;
        // This line will be executed as long as crc is not null, which it isn't in this case.
    }
}
