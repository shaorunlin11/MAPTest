package com.fasterxml.jackson.core.sym;

import org.junit.Test;

public class Name2EqualsZeroCoverageTest {
    @Test
    public void testEqualsWithQuadParameter() {
        Name2 name2 = new Name2("test", 0, 0, 0);
        boolean result = name2.equals(0);
    }
}
