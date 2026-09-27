package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class Base64VariantmissingPaddingMessageTest {
    @Test
    public void testMissingPaddingMessage() throws Exception {
        // Create a Base64Variant that uses padding
        Base64Variant variant = new Base64Variant("test", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 76);

        String message = variant.missingPaddingMessage();

        assertEquals("Unexpected end of base64-encoded String: base64 variant 'test' expects padding (one or more '=' characters) at the end", message);
    }
}
