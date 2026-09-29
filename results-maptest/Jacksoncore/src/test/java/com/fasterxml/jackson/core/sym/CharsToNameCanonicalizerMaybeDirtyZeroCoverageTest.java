package com.fasterxml.jackson.core.sym;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class CharsToNameCanonicalizerMaybeDirtyZeroCoverageTest {
    @Test
    public void testMaybeDirty() {
        // Create an instance of CharsToNameCanonicalizer
        CharsToNameCanonicalizer canonicalizer = CharsToNameCanonicalizer.createRoot();

        // Ensure _hashShared is false
        // Since we cannot modify private fields directly, we rely on the default state
        // which should be false for a root instance

        // Call the method under test
        boolean result = canonicalizer.maybeDirty();

        // Assertion to cover target line 386
        // The line is simply returning !_hashShared, so we need to ensure that _hashShared is false
        // and verify that the return value is true
        // Since we cannot directly check _hashShared, we assume the default state is correct
        // and assert that the method returns true
        // This covers the line in the method
        assertTrue(result);
    }
}
