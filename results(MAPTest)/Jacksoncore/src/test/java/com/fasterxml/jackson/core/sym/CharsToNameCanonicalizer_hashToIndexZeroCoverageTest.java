package com.fasterxml.jackson.core.sym;

import org.junit.Test;

public class CharsToNameCanonicalizer_hashToIndexZeroCoverageTest {
    @Test
    public void test_hashToIndex() {
        // Create an instance of CharsToNameCanonicalizer
        CharsToNameCanonicalizer canonicalizer = CharsToNameCanonicalizer.createRoot();

        // Call the method with a sample rawHash value
        int rawHash = 123456;
        int result = canonicalizer._hashToIndex(rawHash);

        // Ensure the method executes without error
        // Additional assertions can be added if needed
    }
}
