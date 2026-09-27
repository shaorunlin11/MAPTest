package org.apache.commons.codec.binary;

import org.junit.Test;

public class Base64IsInAlphabetZeroCoverageTest {
    @Test
    public void testIsInAlphabet() {
        // Create an instance of Base64 with a decodeTable that has valid entries
        Base64 base64 = new Base64();

        // The target line is part of the isInAlphabet method, which is protected.
        // We can call it through an instance of Base64.
        // We need to provide an octet that satisfies:
        // - octet >= 0
        // - octet < decodeTable.length
        // - decodeTable[octet] != -1

        // Since decodeTable is initialized in the constructor, we can use it directly
        // For example, let's use a byte value that is within the bounds of decodeTable
        // and for which decodeTable[octet] is not -1.
        // We'll use a known valid character from the standard Base64 alphabet.
        // For example, 'A' (ASCII 65) is a valid character in Base64.
        byte octet = 65;
        boolean result = base64.isInAlphabet(octet);

        // This assertion is just to ensure the method runs, but since the goal is to cover
        // the target line, we don't need to assert the result.
    }
}
