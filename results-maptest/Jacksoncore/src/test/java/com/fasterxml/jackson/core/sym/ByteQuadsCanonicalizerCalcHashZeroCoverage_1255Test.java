package com.fasterxml.jackson.core.sym;

import org.junit.Test;

public class ByteQuadsCanonicalizerCalcHashZeroCoverage_1255Test {
    @Test
    public void testCalcHashWithQlenGe4() {
        // Create an instance of ByteQuadsCanonicalizer using available API
        ByteQuadsCanonicalizer canonicalizer = ByteQuadsCanonicalizer.createRoot(12345);

        // Prepare input parameters
        int[] q = { 0x12345678, 0x9ABCDEF0, 0x11223344, 0x55667788 };
        int qlen = 4;

        // Call the method under test
        int result = canonicalizer.calcHash(q, qlen);

        // Add a dummy assertion to satisfy compilation
        assert result != 0;
    }
}
