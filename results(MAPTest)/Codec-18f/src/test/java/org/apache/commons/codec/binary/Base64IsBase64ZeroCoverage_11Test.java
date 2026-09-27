package org.apache.commons.codec.binary;

import org.junit.Test;

public class Base64IsBase64ZeroCoverage_11Test {
    @Test
    public void testIsBase64WithValidInput() {
        byte[] validBase64 = { 65, 66, 67, 68, 69, 70, 71, 72 };
        Base64.isBase64(validBase64);
    }

    @Test
    public void testIsBase64WithInvalidInput() {
        byte[] invalidBase64 = { 65, 66, 67, 68, 69, 70, 71, 72, 123 };
        Base64.isBase64(invalidBase64);
    }

    @Test
    public void testIsBase64WithEmptyArray() {
        byte[] emptyArray = {};
        Base64.isBase64(emptyArray);
    }

    @Test(expected = NullPointerException.class)
    public void testIsBase64WithNullArray() {
        byte[] nullArray = null;
        Base64.isBase64(nullArray);
    }

    @Test
    public void testIsBase64WithWhiteSpaceAndValidChars() {
        byte[] mixedArray = { 65, 66, 32, 67, 68, 9, 69, 70 };
        Base64.isBase64(mixedArray);
    }
}
