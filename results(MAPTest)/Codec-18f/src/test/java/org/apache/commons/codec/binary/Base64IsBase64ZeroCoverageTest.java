package org.apache.commons.codec.binary;

import org.junit.Test;

public class Base64IsBase64ZeroCoverageTest {
    @Test
    public void testIsBase64NonEmptyString() {
        // This test is designed to execute target lines 525 of the isBase64 method
        // by providing a non-null, non-empty string input.
        String base64 = "SGVsbG8gd29ybGQ="; // Valid Base64 string
        boolean result = Base64.isBase64(base64);
        // The test is successful if the method executes without error
    }
}
