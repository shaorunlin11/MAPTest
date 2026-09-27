package org.apache.commons.codec.binary;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;

public class Base64DecodeBase64ZeroCoverage_21Test {
    @Test
    public void testDecodeBase64() {
        // Create a Base64 object using the public constructor
        Base64 base64 = new Base64();

        // Test data that is valid Base64
        byte[] base64Data = "SGVsbG8gV29ybGQ=".getBytes();

        // Call the method under test
        byte[] result = base64.decodeBase64(base64Data);

        // Add an assertion to ensure the method executes and returns a non-null value
        assertNotNull(result);
    }
}
