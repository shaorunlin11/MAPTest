package org.apache.commons.codec.binary;

import org.junit.Test;
import static org.junit.Assert.*;

public class Base64encodeBase64URLSafeTest {

    @Test
    public void testEncodeBase64URLSafeWithNullInput() {
        byte[] result = Base64.encodeBase64URLSafe(null);
        assertNull("encodeBase64URLSafe should return null for null input", result);
    }

    @Test
    public void testEncodeBase64URLSafeWithEmptyInput() {
        byte[] result = Base64.encodeBase64URLSafe(new byte[0]);
        assertNotNull("encodeBase64URLSafe should not return null for empty input", result);
        assertEquals("encodeBase64URLSafe should return empty byte array for empty input", 0, result.length);
    }

    @Test
    public void testEncodeBase64URLSafeWithNonEmptyInput() {
        byte[] input = "Hello, World!".getBytes();
        byte[] result = Base64.encodeBase64URLSafe(input);
        assertNotNull("encodeBase64URLSafe should not return null for non-empty input", result);
        assertFalse("encodeBase64URLSafe should not return empty byte array for non-empty input", result.length == 0);
    }

    @Test
    public void testEncodeBase64URLSafeWithSpecialCharacters() {
        byte[] input = "!@#$%^&*()".getBytes();
        byte[] result = Base64.encodeBase64URLSafe(input);
        assertNotNull("encodeBase64URLSafe should not return null for input with special characters", result);
        assertFalse("encodeBase64URLSafe should not return empty byte array for input with special characters", result.length == 0);
    }
}
