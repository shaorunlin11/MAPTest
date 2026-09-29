package org.apache.commons.codec.binary;

import org.junit.Test;

public class Base64EncodeBase64URLSafeStringZeroCoverageTest {
    @Test
    public void testEncodeBase64URLSafeString() {
        byte[] binaryData = new byte[] { 1, 2, 3 };
        String result = Base64.encodeBase64URLSafeString(binaryData);
        // This test ensures that the method is called and executes the target lines
        // Additional assertions can be added if needed for verification
    }
}
