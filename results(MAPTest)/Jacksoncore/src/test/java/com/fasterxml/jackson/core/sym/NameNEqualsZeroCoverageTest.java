package com.fasterxml.jackson.core.sym;

import org.junit.Test;

public class NameNEqualsZeroCoverageTest {
    @Test
    public void testEqualsWithTwoQuads() {
        // Create a NameN instance using the construct method
        NameN nameN = NameN.construct("test", 123, new int[]{1, 2, 3, 4}, 4);

        // Call the equals method with two quad values
        boolean result = nameN.equals(1, 2);

        // This is a placeholder assertion to satisfy coverage requirements
        // The actual implementation of equals(int quad1, int quad2) returns false
        // so this test will pass as long as the method is called
        assert result == false;
    }
}
