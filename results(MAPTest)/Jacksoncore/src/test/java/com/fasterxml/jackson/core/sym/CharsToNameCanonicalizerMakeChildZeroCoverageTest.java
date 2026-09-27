package com.fasterxml.jackson.core.sym;

import org.junit.Test;

public class CharsToNameCanonicalizerMakeChildZeroCoverageTest {
    @Test
    public void testMakeChild() {
        // Create a valid CharsToNameCanonicalizer instance
        CharsToNameCanonicalizer root = CharsToNameCanonicalizer.createRoot();

        // Ensure _tableInfo is not null and get() returns a valid value
        // This is ensured by the createRoot() method
        int flags = 0;

        // Call the method under test
        CharsToNameCanonicalizer child = root.makeChild(flags);

        // Additional assertions can be added here if needed
    }
}
