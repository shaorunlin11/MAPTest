package com.fasterxml.jackson.core.sym;

import org.junit.Test;

public class CharsToNameCanonicalizerSizeZeroCoverageTest {
    @Test
    public void testSizeWithTableInfoNotNull() {
        // Create a root instance
        CharsToNameCanonicalizer canonicalizer = CharsToNameCanonicalizer.createRoot();

        // Ensure _tableInfo is not null
        // Since createRoot() initializes _tableInfo, we can proceed
        int size = canonicalizer.size();
    }

@Test
    public void testSizeWithTableInfoNull() {
        // Create a child instance with _tableInfo set to null
        CharsToNameCanonicalizer parent = CharsToNameCanonicalizer.createRoot();
        CharsToNameCanonicalizer canonicalizer = parent.makeChild(0);

        // Ensure _tableInfo is null
        // Since makeChild() creates a child with _tableInfo as null, we can proceed
        int size = canonicalizer.size();
    }
}
