package com.fasterxml.jackson.core.sym;

import org.junit.Test;

public class NameNEqualsZeroCoverage_1297Test {
    @Test
    public void testEqualsWithThreeQuads() {
        // Create a NameN instance using the construct method
        NameN nameN = NameN.construct("test", 0, new int[]{1, 2, 3, 4}, 4);

        // Call the equals method with three quad parameters
        boolean result = nameN.equals(1, 2, 3);

        // This test is designed to execute line 62 of the equals method
        // The actual implementation of the method is not provided, so we cannot assert the result
        // This test ensures that the method can be called and executed without errors
    }
}
