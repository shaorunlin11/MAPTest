package org.apache.commons.codec.binary;

import org.junit.Test;

public class Base64DecodeIntegerZeroCoverageTest {
    @Test
    public void testDecodeIntegerTargetLine722() {
        byte[] pArray = new byte[] { (byte) 0x01, (byte) 0x00 };
        byte[] decodedBytes = Base64.decodeBase64(pArray);
        Base64.decodeInteger(pArray);
    }
}
