package org.apache.commons.codec.digest;

import org.junit.Test;

public class PureJavaCrc32CUpdateZeroCoverageTest {
    @Test
    public void testUpdateTargetLine61() {
        PureJavaCrc32C crc = new PureJavaCrc32C();
        byte[] data = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0 };
        crc.update(data, 0, 7);
    }

@Test
    public void testUpdateTargetLine63() {
        PureJavaCrc32C crc = new PureJavaCrc32C();
        // Initialize crc field to non-zero value
        crc.update(0x12345678);
        byte[] data = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        crc.update(data, 0, 9);
    }

@Test
    public void testUpdateTargetLine84() {
        PureJavaCrc32C crc = new PureJavaCrc32C();
        // Initialize crc field to non-zero value
        crc.update(0x12345678);
        byte[] data = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        crc.update(data, 0, 8);
    }
}
